/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package com.oracle.cloud.spring.storage;

import org.springframework.lang.Nullable;

import java.io.IOException;

public interface StorageOutputStreamProvider {

    StorageOutputStream create(String bucket, String key, @Nullable StorageObjectMetadata metadata) throws IOException;

    default StorageOutputStream create(StorageLocation location, @Nullable StorageObjectMetadata metadata) throws IOException {
        return create(location.getBucket(), location.getObject(), metadata);
    }
}
