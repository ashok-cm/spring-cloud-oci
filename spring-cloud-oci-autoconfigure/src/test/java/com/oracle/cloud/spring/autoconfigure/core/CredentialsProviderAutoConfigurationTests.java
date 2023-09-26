/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package com.oracle.cloud.spring.autoconfigure.core;

import com.oracle.bmc.auth.BasicAuthenticationDetailsProvider;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
class CredentialsProviderAutoConfigurationTests {
    private final ApplicationContextRunner contextRunner =
            new ApplicationContextRunner().withConfiguration(AutoConfigurations.of(
                    CredentialsProviderAutoConfiguration.class)).withUserConfiguration(TestConfigurationBean.class);

    @Test
    void testConfigurationValueDefaultsAreAsExpected() {
        contextRunner
                .run(
                        context -> {
                            CredentialsProperties config = context.getBean(CredentialsProperties.class);
                            assertNull(config.getTenantId());
                            assertNotNull(config.getType());
                            assertEquals(config.getType(), CredentialsProperties.ConfigType.FILE);
                        });
    }

    @Test
    void testConfigurationValueConfiguredAreAsExpected() {
        contextRunner
                .withPropertyValues("spring.cloud.oci.config.type=SIMPLE")
                .withPropertyValues("spring.cloud.oci.config.userId=userId")
                .withPropertyValues("spring.cloud.oci.config.tenantId=tenantId")
                .withPropertyValues("spring.cloud.oci.config.fingerprint=fingerprint")
                .withPropertyValues("spring.cloud.oci.config.privateKey=privateKey")
                .withPropertyValues("spring.cloud.oci.config.profile=profile")
                .withPropertyValues("spring.cloud.oci.config.file=file")
                .withPropertyValues("spring.cloud.oci.config.passPhrase=passPhrase")
                .withPropertyValues("spring.cloud.oci.config.region=region")
                .run(
                        context -> {
                            CredentialsProperties config = context.getBean(CredentialsProperties.class);
                            assertEquals(config.getType(), CredentialsProperties.ConfigType.SIMPLE);
                            assertEquals(config.getUserId(), "userId");
                            assertEquals(config.getTenantId(), "tenantId");
                            assertEquals(config.getFingerprint(), "fingerprint");
                            assertEquals(config.getPrivateKey(), "privateKey");
                            assertEquals(config.getProfile(), "profile");
                            assertEquals(config.getFile(), "file");
                            assertEquals(config.getPassPhrase(), "passPhrase");
                            assertEquals(config.getRegion(), "region");
                        });
    }

    @Configuration
    static class TestConfigurationBean {
        @Bean
        BasicAuthenticationDetailsProvider credentialsProvider() {
            return mock(BasicAuthenticationDetailsProvider.class);
        }
    }
}