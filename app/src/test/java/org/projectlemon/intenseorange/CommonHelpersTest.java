package org.projectlemon.intenseorange;

import junit.framework.Assert;

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

    @Test
    public void testServerNameLength() {
        String s = "linus";
        int length = s.length();
        int newLength = CommonHelpers.extractServerName(s+".protocol.com").length();
        Assert.assertEquals(newLength, length);
    }

    @Test
    public void testServerNameCorrectness() {
        String s = "lagerhjelm";
        String s2 = "._tcp.se";
        String s3 = CommonHelpers.extractServerName(s+s2);
        Assert.assertEquals(s3,s);
    }
}
