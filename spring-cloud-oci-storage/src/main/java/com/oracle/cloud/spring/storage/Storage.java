/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package com.oracle.cloud.spring.storage;

import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.responses.CreateBucketResponse;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.io.InputStream;

public interface Storage {

    OracleStorageResource download(String bucketName, String key);

    OracleStorageResource download(String bucketName, String key, String version);

    OracleStorageResource upload(String bucketName, String key, InputStream inputStream,
                                 @Nullable StorageObjectMetadata objectMetadata) throws IOException;

    default OracleStorageResource upload(String bucketName, String key, InputStream inputStream) throws IOException {
        return upload(bucketName, key, inputStream, null);
    }

    OracleStorageResource store(String bucketName, String key, Object object) throws IOException;

    <T> T read(String bucketName, String key, Class<T> clazz);

    ObjectStorageClient getClient();

    CreateBucketResponse createBucket(String bucketName);

    CreateBucketResponse createBucket(String bucketName, String compartmentId);

    void deleteBucket(String bucketName);

    void deleteObject(String bucketName, String key);

    String getNamespaceName();
}
