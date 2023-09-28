/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package com.example.configrefreshsample.core;

import com.oracle.bmc.ons.NotificationControlPlane;
import com.oracle.bmc.ons.NotificationDataPlane;
import com.oracle.bmc.ons.responses.CreateSubscriptionResponse;
import com.oracle.bmc.ons.responses.CreateTopicResponse;
import com.oracle.bmc.ons.responses.PublishMessageResponse;

/**
 * Interface for defining OCI logging module.
 */
public interface Notification {

    /**
     * Direct instance of OCI Java SDK NotificationDataPlane Client.
     * @return NotificationDataPlane
     */
    NotificationDataPlane getNotificationDataPlaneClient();

    /**
     * Direct instance of OCI Java SDK NotificationControlPlane Client.
     * @return NotificationControlPlane
     */
    NotificationControlPlane getNotificationControlPlaneClient();

    String listSubscriptions(String topicId, String compartmentId);

}
