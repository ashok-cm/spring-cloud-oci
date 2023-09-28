/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package com.example.configrefreshsample;

import com.example.configrefreshsample.core.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("demoapp/api/notifications")
public class NotificationController {

    @Autowired
    Notification notification;

    @GetMapping(value = "/subscriptions")
    ResponseEntity<?> listNotificationSubscriptions(@RequestParam String topicId,
                                                    @RequestParam String compartmentId) {
        String response = notification.listSubscriptions(topicId, compartmentId);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/hello")
    ResponseEntity<?> hello() {
        return ResponseEntity.ok().body("Hello World!");
    }

}
