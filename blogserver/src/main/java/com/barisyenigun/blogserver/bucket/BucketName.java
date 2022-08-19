package com.barisyenigun.blogserver.bucket;

public enum BucketName {
    PROFILE_IMAGE("blog-application-image-upload-123");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
