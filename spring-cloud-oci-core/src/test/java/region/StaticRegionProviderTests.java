/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package region;

import com.oracle.bmc.Region;
import com.oracle.cloud.spring.core.region.StaticRegionProvider;
import org.junit.Assert;
import org.junit.Test;

public class StaticRegionProviderTests {

    @Test
    public void getCompartmentOCID() {
        Region inputRegion = Region.US_ASHBURN_1;
        StaticRegionProvider provider = new StaticRegionProvider(inputRegion.getRegionId());
        Region outputRegion = provider.getRegion();
        Assert.assertNotNull(outputRegion);
        Assert.assertEquals(outputRegion.getRegionId(), inputRegion.getRegionId());
    }

}
