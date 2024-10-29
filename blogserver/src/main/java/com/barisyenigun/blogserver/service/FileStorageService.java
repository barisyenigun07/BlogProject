package com.barisyenigun.blogserver.service;


import com.barisyenigun.blogserver.bucket.BucketName;
import com.barisyenigun.blogserver.exception.FileException;
import com.barisyenigun.blogserver.util.StorageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.*;

@Service
public class FileStorageService {

    private final StorageUtil storageUtil;

    @Autowired
    public FileStorageService(StorageUtil storageUtil){
        this.storageUtil = storageUtil;
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
            storageUtil.upload(path, filename, Optional.of(metadata), file.getInputStream());
            return filename;
        }
        catch (IOException e){
            throw new FileException("File upload failed!");
        }
    }

    public void deleteFile(String destination, String filename){
        storageUtil.delete(BucketName.STORAGE_BUCKET.getBucketName(), String.format("%s/%s", destination, filename));
    }
}
