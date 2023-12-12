package com.barisyenigun.blogserver.util;


import com.barisyenigun.blogserver.bucket.BucketName;
import com.barisyenigun.blogserver.exception.FileException;
import com.barisyenigun.blogserver.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.*;

@Service
public class FileUtil {

    private final StorageService storageService;

    @Autowired
    public FileUtil(StorageService storageService){
        this.storageService = storageService;
    }

    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private void isProperType(MultipartFile file, String prefix){
        String mimeType;
        try {
            mimeType = Files.probeContentType(Path.of(Objects.requireNonNull(file.getOriginalFilename())));
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }

        if (!mimeType.startsWith(prefix)){
            throw new FileException("File is in improper type!");
        }
    }

    private String normalizeFilename(String filename) {
        String normalizedFilename = Normalizer.normalize(filename, Normalizer.Form.NFD);
        return normalizedFilename.replaceAll("[^\\p{ASCII}]", "");
    }

    public String uploadFile(MultipartFile file, String prefix, String destination){
        isProperType(file, prefix);
        Map<String, String> metadata = extractMetadata(file);
        String path = String.format("%s/%s", BucketName.STORAGE_BUCKET.getBucketName(), destination);
        String filename = String.format("%s-%s", UUID.randomUUID(), normalizeFilename(file.getOriginalFilename()));

        try {
            storageService.upload(path, filename, Optional.of(metadata), file.getInputStream());
            return filename;
        }
        catch (IOException e){
            throw new FileException("File upload failed!");
        }
    }

    public byte[] downloadFile(String destination, String filename){
        return storageService.download(String.format("%s/%s", BucketName.STORAGE_BUCKET.getBucketName(), destination), filename);
    }

    public void deleteFile(String destination, String filename){
        storageService.delete(BucketName.STORAGE_BUCKET.getBucketName(), String.format("%s/%s", destination, filename));
    }
}
