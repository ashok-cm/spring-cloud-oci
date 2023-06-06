/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package com.oracle.cloud.spring.sample.s3.springcloudocis3sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.oracle.cloud.sdk.spring"})
public class SpringCloudOciStorageSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudOciStorageSampleApplication.class, args);
    }
}
