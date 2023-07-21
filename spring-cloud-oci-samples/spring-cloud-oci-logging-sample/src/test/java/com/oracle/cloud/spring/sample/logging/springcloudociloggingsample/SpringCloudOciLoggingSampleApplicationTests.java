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

	static String privateKeyFilePath = System.getProperty("privateKey") != null ? System.getProperty("privateKey") :
			System.getenv().get("privateKey");
	static String privateKeyContent = System.getProperty("privateKeyContent") != null ? System.getProperty("privateKeyContent") :
			System.getenv().get("privateKeyContent");
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
