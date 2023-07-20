/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package compartment;

import com.oracle.cloud.spring.core.compartment.StaticCompartmentProvider;
import org.junit.Assert;
import org.junit.Test;

public class StaticCompartmentProviderTests {

    @Test
    public void getCompartmentOCID() {
        String inputCompartmentOCID = "demo";
        StaticCompartmentProvider provider = new StaticCompartmentProvider(inputCompartmentOCID);
        String outputCompartmentOCID = provider.getCompartmentOCID();
        Assert.assertNotNull(outputCompartmentOCID);
        Assert.assertEquals(outputCompartmentOCID, inputCompartmentOCID);
    }

}
