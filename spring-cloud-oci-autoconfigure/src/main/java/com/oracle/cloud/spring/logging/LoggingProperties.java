package com.oracle.cloud.spring.logging;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.Nullable;

@ConfigurationProperties(prefix = LoggingProperties.PREFIX)
public class LoggingProperties {
    public static final String PREFIX = "spring.cloud.oci.logging";


    @Nullable
    private String logId;

    @Nullable
    public String getLogId() {
        return logId;
    }

    public void setLogId(@Nullable String logId) {
        this.logId = logId;
    }

}
