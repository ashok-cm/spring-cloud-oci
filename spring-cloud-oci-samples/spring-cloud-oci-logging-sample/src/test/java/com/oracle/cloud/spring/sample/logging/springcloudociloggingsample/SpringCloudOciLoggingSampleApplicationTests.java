/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package com.oracle.cloud.spring.sample.logging.springcloudociloggingsample;

import com.oracle.bmc.loggingingestion.responses.PutLogsResponse;
import com.oracle.cloud.spring.logging.Logging;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.Assert;


@SpringBootTest
/** @EnabledIfSystemProperty(named = "it.logging", matches = "true") **/
@TestPropertySource(locations="classpath:application-test.properties")
class SpringCloudOciLoggingSampleApplicationTests {

	@Autowired
	Logging logging;

	@Test
	void testLoggingApis() {

		PutLogsResponse response = logging.putLogs("error starting application");
		Assert.isTrue(response.getOpcRequestId() != null);
	}

}
