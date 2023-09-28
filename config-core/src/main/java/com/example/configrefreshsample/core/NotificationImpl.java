/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package com.example.configrefreshsample.core;

import com.oracle.bmc.ons.NotificationControlPlane;
import com.oracle.bmc.ons.NotificationDataPlane;
import com.oracle.bmc.ons.requests.*;
import com.oracle.bmc.ons.responses.*;

/**
 * Implementation for the OCI Notification module.
 */
public class NotificationImpl implements Notification {
    private final NotificationDataPlane notificationDataPlane;
    private final NotificationControlPlane notificationControlPlane;

    public NotificationImpl(NotificationDataPlane notificationDataPlane, NotificationControlPlane notificationControlPlane) {
        this.notificationDataPlane = notificationDataPlane;
        this.notificationControlPlane = notificationControlPlane;
    }

    /**
     * Direct instance of OCI Java SDK NotificationDataPlane Client.
     * @return NotificationDataPlane
     */
    public NotificationDataPlane getNotificationDataPlaneClient() {
        return notificationDataPlane;
    }

    /**
     * Direct instance of OCI Java SDK NotificationControlPlane Client.
     * @return NotificationControlPlane
     */
    public NotificationControlPlane getNotificationControlPlaneClient() {
        return notificationControlPlane;
    }

    @Override
    public String listSubscriptions(String topicId, String compartmentId) {
        ListSubscriptionsRequest request = ListSubscriptionsRequest.builder().topicId(topicId).
                compartmentId(compartmentId).build();
        ListSubscriptionsResponse response = notificationDataPlane.listSubscriptions(request);
        System.out.println(" result subscriptions : " + response.getItems());
//        String jsonArrayString = OCIObjectMapper.toPrintableString(response.getItems());
        return response.getItems().toString();
    }

}
