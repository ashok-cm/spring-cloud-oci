/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package com.oracle.cloud.spring.storage;

import com.oracle.bmc.objectstorage.ObjectStorageClient;
import org.springframework.lang.Nullable;
import org.springframework.util.unit.DataSize;

import java.io.IOException;

public class DefaultStorageOutputStreamProvider implements StorageOutputStreamProvider {
    private final ObjectStorageClient osClient;

    @Nullable
    private final DataSize bufferSize;

    public DefaultStorageOutputStreamProvider(ObjectStorageClient osClient) {
        this(osClient, null);
    }

    public DefaultStorageOutputStreamProvider(ObjectStorageClient osClient, @Nullable DataSize bufferSize) {
        this.osClient = osClient;
        this.bufferSize = bufferSize;
    }

    @Override
    public StorageOutputStream create(String bucket, String key, @Nullable StorageObjectMetadata metadata) throws IOException {
        return null;
    }
}