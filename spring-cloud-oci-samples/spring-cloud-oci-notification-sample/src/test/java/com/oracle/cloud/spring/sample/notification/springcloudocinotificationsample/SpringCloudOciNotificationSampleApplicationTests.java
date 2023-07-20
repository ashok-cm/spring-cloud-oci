/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package com.oracle.cloud.spring.sample.notification.springcloudocinotificationsample;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.bmc.ons.model.SubscriptionSummary;
import com.oracle.cloud.spring.notification.Notification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
/** @EnabledIfSystemProperty(named = "it.notification", matches = "true") **/
@TestPropertySource(locations="classpath:application-test.properties")
class SpringCloudOciNotificationSampleApplicationTests {

	@Autowired
	Notification notification;

	@Value("${existingTopicId}")
	String existingTopicId;

	@Value("${compartmentId}")
	String compartmentId;

	@Test
	void testNotificationApis() throws Exception {
		testListSubscriptions();
		/**
		 * TODO : add test for get subscription, create topic, publish message also
		 */
	}

	private void testListSubscriptions() throws Exception {
		String listSubscriptions = notification.listSubscriptions(existingTopicId,
				compartmentId);
		ObjectMapper objectMapper = new ObjectMapper();
		List<SubscriptionSummary> notificationList =
				objectMapper.readValue(listSubscriptions, new TypeReference<List<SubscriptionSummary>>(){});
		Assert.isTrue(notificationList.size() > 0);
	}
}
