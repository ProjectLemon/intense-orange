package org.projectlemon.intenseorange;

import android.app.Application;
import android.test.ApplicationTestCase;

import junit.framework.Assert;

import org.junit.Test;
import org.projectlemon.intenseorange.controller.implementations.NetworkController;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    @Test
    public void hejsan(){
        NetworkController nc = new NetworkController(null, null, null);
        Assert.assertEquals(nc, null);
    }
}