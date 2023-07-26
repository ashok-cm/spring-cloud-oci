/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package com.oracle.cloud.spring.sample.storage.springcloudocistoragesample;

import com.oracle.cloud.spring.sample.common.util.FileUtils;
import com.oracle.cloud.spring.storage.Storage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.File;
import java.io.IOException;

@SpringBootTest
@EnabledIfSystemProperty(named = "it.storage", matches = "true")
@TestPropertySource(locations="classpath:application-test.properties")
class SpringCloudOciStorageSampleApplicationTests {

    public static final String PRIVATE_KEY_PATH = "privateKey";
    public static final String PRIVATE_KEY_CONTENT = "privateKeyContent";
    public static final String TEST_BUCKET = "bucketName";

    public static final String privateKeyFilePath = System.getProperty("user.home") + File.separator + "privatekey.pem";
    public static final String privateKeyContent = System.getProperty(PRIVATE_KEY_CONTENT) != null ? System.getProperty(PRIVATE_KEY_CONTENT) :
            System.getenv().get(PRIVATE_KEY_CONTENT);
    public static final String testBucket = System.getProperty(TEST_BUCKET) != null ? System.getProperty(TEST_BUCKET) :
            System.getenv().get(TEST_BUCKET);

    @BeforeAll
    static void beforeAll() throws Exception {
        System.setProperty(PRIVATE_KEY_PATH, privateKeyFilePath);
        FileUtils.createFile(privateKeyFilePath, privateKeyContent.replace("\\n", "\n"));
    }

    @Autowired
    Storage storage;

    @Test
    void testFileUpload() throws IOException {
        ActivityInfo ainfo = new ActivityInfo("Hello from Storage integration test");
        storage.store(testBucket, ainfo.getFileName(), ainfo);
    }

    @AfterAll
    static void AfterAll() {
        FileUtils.deleteFile(privateKeyFilePath);
    }

    private class ActivityInfo {
        long time = System.currentTimeMillis();
        String message;

        public ActivityInfo(String message) { this.message = message; }

        public String getFileName() {
            return "activity_" + time + ".json";
        }

        public long getTime() {
            return time;
        }

        public String getMessage() {
            return message;
        }
    }
}


