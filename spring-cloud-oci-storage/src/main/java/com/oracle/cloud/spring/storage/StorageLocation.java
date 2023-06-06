/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package com.oracle.cloud.spring.storage;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public class StorageLocation {

    private static final String OCS_PROTOCOL_PREFIX = "ocs://";

    private static final String PATH_DELIMITER = "/";

    private static final String VERSION_DELIMITER = "^";

    private final String bucket;

    private final String object;

    @Nullable
    private final String version;

    StorageLocation(String bucket, String object) {
        this(bucket, object, null);
    }

    StorageLocation(String bucket, String object, @Nullable String version) {
        Assert.notNull(bucket, "bucket is required");
        Assert.notNull(object, "object is required");

        this.bucket = bucket;
        this.object = object;
        this.version = version;
    }

    String getBucket() {
        return bucket;
    }

    String getObject() {
        return object;
    }

    @Nullable
    String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "Location{" + "bucket='" + bucket + '\'' + ", object='" + object + '\'' + ", version='" + version + '\''
                + '}';
    }

    @Nullable
    public static StorageLocation resolve(String location) {
        if (isSimpleStorageResource(location)) {
            return new StorageLocation(resolveBucketName(location), resolveObjectName(location), resolveVersionId(location));
        }

        return null;
    }

    static boolean isSimpleStorageResource(String location) {
        Assert.notNull(location, "Location must not be null");
        return location.toLowerCase().startsWith(OCS_PROTOCOL_PREFIX);
    }

    private static String resolveBucketName(String location) {
        int bucketEndIndex = location.indexOf(PATH_DELIMITER, OCS_PROTOCOL_PREFIX.length());
        if (bucketEndIndex == -1 || bucketEndIndex == OCS_PROTOCOL_PREFIX.length()) {
            throw new IllegalArgumentException("The location :'" + location + "' does not contain a valid bucket name");
        }
        return location.substring(OCS_PROTOCOL_PREFIX.length(), bucketEndIndex);
    }

    private static String resolveObjectName(String location) {
        int bucketEndIndex = location.indexOf(PATH_DELIMITER, OCS_PROTOCOL_PREFIX.length());
        if (bucketEndIndex == -1 || bucketEndIndex == OCS_PROTOCOL_PREFIX.length()) {
            throw new IllegalArgumentException("The location :'" + location + "' does not contain a valid bucket name");
        }

        if (location.contains(VERSION_DELIMITER)) {
            return resolveObjectName(location.substring(0, location.indexOf(VERSION_DELIMITER)));
        }

        int endIndex = location.length();
        if (location.endsWith(PATH_DELIMITER)) {
            endIndex--;
        }

        if (bucketEndIndex >= endIndex) {
            return "";
        }

        return location.substring(++bucketEndIndex, endIndex);
    }

    @Nullable
    private static String resolveVersionId(String location) {
        int objectNameEndIndex = location.indexOf(VERSION_DELIMITER, OCS_PROTOCOL_PREFIX.length());
        if (objectNameEndIndex == -1 || location.endsWith(VERSION_DELIMITER)) {
            return null;
        }

        if (objectNameEndIndex == OCS_PROTOCOL_PREFIX.length()) {
            throw new IllegalArgumentException("The location :'" + location + "' does not contain a valid bucket name");
        }

        return location.substring(++objectNameEndIndex, location.length());
    }
}
