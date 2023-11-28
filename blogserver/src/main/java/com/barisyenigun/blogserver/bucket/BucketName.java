package com.barisyenigun.blogserver.bucket;

public enum BucketName {
    STORAGE_BUCKET("blog-application-storage-bucket");

    private final String bucketName;

    BucketName(String bucketName){
        this.bucketName = bucketName;
    }

    public String getBucketName(){
        return bucketName;
    }
}
