/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package com.oracle.cloud.spring.sample.logging.springcloudociloggingsample;

import com.oracle.bmc.loggingingestion.responses.PutLogsResponse;
import com.oracle.cloud.spring.core.util.FileUtils;
import com.oracle.cloud.spring.logging.Logging;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.Assert;

/**
 * Environment variables needed to run this tests are :
 * all variables in application-test.properties files
 */

@SpringBootTest
@EnabledIfSystemProperty(named = "it.logging", matches = "true")
@TestPropertySource(locations="classpath:application-test.properties")
class SpringCloudOciLoggingSampleApplicationTests {

	public static final String PRIVATE_KEY_PATH = "privateKey";
	public static final String PRIVATE_KEY_CONTENT = "privateKeyContent";

	public static final String privateKeyFilePath = System.getProperty(PRIVATE_KEY_PATH) != null ? System.getProperty(PRIVATE_KEY_PATH) :
			System.getenv().get(PRIVATE_KEY_PATH);
	public static final String privateKeyContent = System.getProperty(PRIVATE_KEY_CONTENT) != null ? System.getProperty(PRIVATE_KEY_CONTENT) :
			System.getenv().get(PRIVATE_KEY_CONTENT);
	@BeforeAll
	static void beforeAll() {
		try {
			FileUtils.createFile(privateKeyFilePath, privateKeyContent.replace("\\n", "\n"));
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Autowired
	Logging logging;

	@Test
	void testLoggingApis() {

		PutLogsResponse response = logging.putLogs("error starting application");
		Assert.notNull(response.getOpcRequestId());
	}

	@AfterAll
	static void AfterAll() {
		FileUtils.deleteFile(privateKeyFilePath);
	}
}
