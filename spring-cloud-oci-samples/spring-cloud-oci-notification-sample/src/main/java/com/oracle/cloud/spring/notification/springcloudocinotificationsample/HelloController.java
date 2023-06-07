/*
 ** Copyright (c) 2023 Oracle, Inc.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.
 */

package com.oracle.cloud.spring.notification.springcloudocinotificationsample;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demoapp/api")
@Tag(name="Hello World APIs")
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello World From Notification Service";
    }
}
