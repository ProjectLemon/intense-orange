package org.projectlemon.intenseorange;

import org.junit.Test;
import org.projectlemon.intenseorange.model.utilities.helpers.CommonHelpers;

/**
 * Unit tests for the CommonHelpers.java class
 *
 * Created by linuslagerhjelm on 16-01-19.
 */
public class CommonHelpersTest {

    @Test
    public void testStringNull() {
        String s = null;
        assert(CommonHelpers.isNullOrEmpty(s));
    }

    @Test
    public void testStringEmpty() {
        String s = "";
        assert(CommonHelpers.isNullOrEmpty(s));
    }
}
