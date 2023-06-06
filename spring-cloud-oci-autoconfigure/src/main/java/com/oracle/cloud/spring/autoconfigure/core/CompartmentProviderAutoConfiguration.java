/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package com.oracle.cloud.spring.autoconfigure.core;

import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.cloud.spring.compartment.CompartmentProvider;
import com.oracle.cloud.spring.compartment.StaticCompartmentProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({AuthenticationDetailsProvider.class})
@EnableConfigurationProperties(CompartmentProperties.class)
public class CompartmentProviderAutoConfiguration {

    private final CompartmentProperties properties;

    public CompartmentProviderAutoConfiguration(CompartmentProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public CompartmentProvider compartmentProvider() {
        return createCompartmentProvider(properties);
    }

    public static CompartmentProvider createCompartmentProvider(CompartmentProperties properties) {
        if (properties.getStatic() != null && properties.isStatic()) {
            return new StaticCompartmentProvider(properties.getStatic().trim());
        }

        return null;
    }
}
