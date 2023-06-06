/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package com.oracle.cloud.spring.storage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;

public class JacksonJSONStorageObjectConverter implements StorageObjectConverter {
    private final ObjectMapper objectMapper;

    public JacksonJSONStorageObjectConverter(ObjectMapper objectMapper) {
        Assert.notNull(objectMapper, "objectMapper is required");
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> byte[] write(T object) {
        Assert.notNull(object, "object is required");
        try {
            return objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw new StorageException("Failed to serialize object to JSON", e);
        }
    }

    @Override
    public <T> T read(InputStream is, Class<T> clazz) {
        Assert.notNull(is, "InputStream is required");
        Assert.notNull(clazz, "Clazz is required");
        try {
            return objectMapper.readValue(is, clazz);
        } catch (IOException e) {
            throw new StorageException("Failed to deserialize object from JSON", e);
        }
    }

    @Override
    public String contentType() {
        return "application/json";
    }
}
