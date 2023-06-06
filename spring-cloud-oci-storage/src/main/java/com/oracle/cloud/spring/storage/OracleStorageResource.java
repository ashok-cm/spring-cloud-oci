/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package com.oracle.cloud.spring.storage;

import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.requests.GetNamespaceRequest;
import com.oracle.bmc.objectstorage.requests.GetObjectRequest;
import com.oracle.bmc.objectstorage.responses.GetNamespaceResponse;
import com.oracle.bmc.objectstorage.responses.GetObjectResponse;
import org.springframework.core.io.AbstractResource;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.io.InputStream;

public class OracleStorageResource extends AbstractResource {

    ObjectStorageClient osClient;
    StorageOutputStreamProvider storageOutputStreamProvider;
    StorageLocation location;

    @Nullable
    private StorageObjectMetadata objectMetadata;

    @Nullable
    public static OracleStorageResource create(String location, ObjectStorageClient osClient,
                                               StorageOutputStreamProvider storageOutputStreamProvider) {
        StorageLocation locationObject = StorageLocation.resolve(location);
        if (locationObject != null) {
            return new OracleStorageResource(locationObject, osClient, storageOutputStreamProvider);
        }

        return null;
    }

    public OracleStorageResource(String bucketName, String objectName, ObjectStorageClient osClient,
                                 StorageOutputStreamProvider storageOutputStreamProvider) {
        this(bucketName, objectName, null, osClient, storageOutputStreamProvider);
    }

    public OracleStorageResource(String bucketName, String objectName, String version,
                                 ObjectStorageClient osClient, StorageOutputStreamProvider storageOutputStreamProvider) {
        this(new StorageLocation(bucketName, objectName, version), osClient, storageOutputStreamProvider);
    }

    public OracleStorageResource(StorageLocation location, ObjectStorageClient osClient,
                                 StorageOutputStreamProvider storageOutputStreamProvider) {
        this.location = location;
        this.osClient = osClient;
        this.storageOutputStreamProvider = storageOutputStreamProvider;
    }

    @Override
    public String getDescription() {
        return location.toString();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        GetNamespaceResponse namespaceResponse =
                osClient.getNamespace(GetNamespaceRequest.builder().build());
        String namespaceName = namespaceResponse.getValue();

        GetObjectResponse getResponse =
                osClient.getObject(
                        GetObjectRequest.builder()
                                .namespaceName(namespaceName)
                                .bucketName(location.getBucket())
                                .objectName(location.getObject())
                                .versionId(location.getVersion())
                                .build());
        return getResponse.getInputStream();
    }

    public void setObjectMetadata(@Nullable StorageObjectMetadata objectMetadata) {
        this.objectMetadata = objectMetadata;
    }
}
