package com.barisyenigun.blogserver.util;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.barisyenigun.blogserver.exception.FileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;
import java.util.Optional;

@Service
public class StorageUtil {
    private final AmazonS3 s3;


    @Autowired
    public StorageUtil(AmazonS3 s3){
        this.s3 = s3;
    }

    public void upload(String path, String fileName, Optional<Map<String, String>> optionalMetadata, InputStream inputStream){
        ObjectMetadata objectMetadata = new ObjectMetadata();
        optionalMetadata.ifPresent(map -> {
            if (!map.isEmpty()) {
                map.forEach(objectMetadata::addUserMetadata);
            }
        });

        try {
            s3.putObject(path, fileName, inputStream, objectMetadata);
        }
        catch (AmazonServiceException e) {
            throw new FileException("File upload failed!");
        }
    }

    public void delete(String bucketName, String key){
        s3.deleteObject(bucketName, key);
    }

}
