/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package com.oracle.cloud.spring.notification;

import com.oracle.bmc.ons.responses.*;

public interface Notification {
    PublishMessageResponse publishMessage(String topicId, String title, String message);
    CreateSubscriptionResponse createSubscription(String compartmentId, String topicId, String protocol, String endpoint);
    String getSubscription(String subscriptionId);

    String listSubscriptions(String topicI, String compartmentId);
    CreateTopicResponse createTopic(String topicName, String compartmentId);
}
