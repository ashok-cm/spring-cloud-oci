/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package com.oracle.cloud.spring.compartment;

import org.springframework.util.Assert;

public class StaticCompartmentProvider implements CompartmentProvider {

    private final String compartmentOCID;

    public StaticCompartmentProvider(String compartmentOCID) {
        Assert.notNull(compartmentOCID, "compartmentOCID is required");
        this.compartmentOCID = compartmentOCID;
    }

    @Override
    public String getCompartmentOCID() {
        return compartmentOCID;
    }

}
