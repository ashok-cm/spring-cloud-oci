/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package com.oracle.cloud.spring.sample.logging.springcloudociloggingsample;

import com.oracle.bmc.loggingingestion.responses.PutLogsResponse;
import com.oracle.cloud.spring.logging.Logging;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileWriter;


@SpringBootTest
/** @EnabledIfSystemProperty(named = "it.logging", matches = "true") **/
@TestPropertySource(locations="classpath:application-test.properties")
class SpringCloudOciLoggingSampleApplicationTests {

	@BeforeAll
	static void beforeAll() throws Exception {
		String privateKeyFilePath = System.getenv().get("privateKey");
		File f = new File(privateKeyFilePath);

		if(!f.exists() || f.isDirectory()) {
			FileWriter myWriter = new FileWriter(privateKeyFilePath);
			String privateKeyContent = System.getenv().get("privateKeyContent");
			myWriter.write(privateKeyContent);
			myWriter.close();
		}
	}

	@Autowired
	Logging logging;

	@Test
	void testLoggingApis() {

		PutLogsResponse response = logging.putLogs("error starting application");
		Assert.notNull(response.getOpcRequestId());
	}

}
