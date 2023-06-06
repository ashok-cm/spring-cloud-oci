/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package com.oracle.cloud.spring.storage;

import java.io.InputStream;

public interface StorageObjectConverter {
    <T> byte[] write(T object);

    <T> T read(InputStream is, Class<T> clazz);

    String contentType();
}
