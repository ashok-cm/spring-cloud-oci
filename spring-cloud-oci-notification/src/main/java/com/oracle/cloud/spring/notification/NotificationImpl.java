/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package com.oracle.cloud.spring.notification;

import com.oracle.bmc.ons.NotificationControlPlane;
import com.oracle.bmc.ons.NotificationDataPlane;
import com.oracle.bmc.ons.model.CreateSubscriptionDetails;
import com.oracle.bmc.ons.model.CreateTopicDetails;
import com.oracle.bmc.ons.model.MessageDetails;
import com.oracle.bmc.ons.model.PublishResult;
import com.oracle.bmc.ons.requests.*;
import com.oracle.bmc.ons.responses.*;
import com.oracle.cloud.spring.core.util.OCIObjectMapper;

public class NotificationImpl implements Notification {
    NotificationDataPlane notificationDataPlane;
    NotificationControlPlane notificationControlPlane;

    public NotificationImpl(NotificationDataPlane notificationDataPlane, NotificationControlPlane notificationControlPlane) {
        this.notificationDataPlane = notificationDataPlane;
        this.notificationControlPlane = notificationControlPlane;
    }

    @Override
    public PublishMessageResponse publishMessage(String topicId, String title, String message) {

        MessageDetails.Builder messageDetailsBuilder = MessageDetails.builder();
        messageDetailsBuilder.title(title).body(message);
        MessageDetails messageDetails = messageDetailsBuilder.build();

        PublishMessageRequest.Builder builder = PublishMessageRequest.builder();
        builder.topicId(topicId).messageDetails(messageDetails);

        PublishMessageRequest request = builder.build();
        PublishMessageResponse response = notificationDataPlane.publishMessage(request);
        PublishResult result = response.getPublishResult();
        System.out.println(" result messageId : " + result.getMessageId());
        return response;
    }

    @Override
    public CreateSubscriptionResponse createSubscription(String compartmentId, String topicId, String protocol, String endpoint) {
        CreateSubscriptionDetails details = CreateSubscriptionDetails.builder().compartmentId(compartmentId).topicId(topicId).protocol(protocol).endpoint(endpoint).build();
        CreateSubscriptionRequest request = CreateSubscriptionRequest.builder().createSubscriptionDetails(details).build();
        CreateSubscriptionResponse response = notificationDataPlane.createSubscription(request);
        System.out.println(" result subscriptionId : " + response.getSubscription().getId());
        return response;
    }

    @Override
    public String getSubscription(String subscriptionId) {
        GetSubscriptionRequest request = GetSubscriptionRequest.builder().subscriptionId(subscriptionId).build();
        GetSubscriptionResponse response = notificationDataPlane.getSubscription(request);
        System.out.println(" result subscription : " + response.getSubscription());
        String jsonObjectString = OCIObjectMapper.toPrintableString(response.getSubscription());
        return jsonObjectString;
    }

    @Override
    public String listSubscriptions(String topicId, String compartmentId) {
        ListSubscriptionsRequest request = ListSubscriptionsRequest.builder().topicId(topicId).
                compartmentId(compartmentId).build();
        ListSubscriptionsResponse response = notificationDataPlane.listSubscriptions(request);
        System.out.println(" result subscriptions : " + response.getItems());
        String jsonArrayString = OCIObjectMapper.toPrintableString(response.getItems());
        return jsonArrayString;
    }
    @Override
    public CreateTopicResponse createTopic(String topicName, String compartmentId) {
        CreateTopicDetails details = CreateTopicDetails.builder().name(topicName).compartmentId(compartmentId).build();
        CreateTopicRequest request = CreateTopicRequest.builder().createTopicDetails(details).build();
        CreateTopicResponse response = notificationControlPlane.createTopic(request);
        System.out.println(" result topicId : " + response.getNotificationTopic().getTopicId());
        return response;
    }


}
