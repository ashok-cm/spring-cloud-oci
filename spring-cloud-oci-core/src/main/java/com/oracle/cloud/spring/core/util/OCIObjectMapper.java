/*
 * Copyright (c) 2018 Oracle and/or its affiliates. All rights reserved.
 */
package com.oracle.cloud.spring.core.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.TimeZone;

public class OCIObjectMapper {
    private static String DEFAULT_FILTER = "explicitlySetFilter";
    private static String DEFAULT_TIME_ZONE = "GMT";

    private static final ObjectMapper objectMapper = createObjectMapper(false);

    public static String toPrintableString(Object object) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().
                    writeValueAsString(object);
        } catch (Exception ex) {
            return null;
        }
    }

    private static ObjectMapper createObjectMapper(boolean excludeNullValues) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (excludeNullValues) {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        df.setTimeZone(TimeZone.getTimeZone(DEFAULT_TIME_ZONE));
        objectMapper.setDateFormat(df);
        final FilterProvider filter = new SimpleFilterProvider()
                .addFilter(DEFAULT_FILTER, SimpleBeanPropertyFilter.serializeAllExcept(Collections.EMPTY_SET));
        objectMapper.setFilterProvider(filter);
        return objectMapper;
    }
}
