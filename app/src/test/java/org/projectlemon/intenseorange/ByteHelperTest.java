package org.projectlemon.intenseorange;


import org.junit.Test;
import org.projectlemon.intenseorange.model.utilities.helpers.ByteHelper;
import static org.junit.Assert.*;
/**
 * Created by Jenny on 2015-12-01.
 */
public class ByteHelperTest {

    @Test
    public void tryAddByte(){
        byte b = 0;
        ByteHelper byteHelper = new ByteHelper(b);
        assertEquals(byteHelper.size(), 1);
    }

    @Test
    public void tryAddShort(){
        short s =0;
        ByteHelper byteHelper = new ByteHelper();
        byteHelper.addShort(s);
        assertEquals(byteHelper.size(), 2);
    }

    @Test
    public void tryAddInt(){
        int i =0;
        ByteHelper byteHelper = new ByteHelper();
        byteHelper.addInt(i);
        assertEquals(byteHelper.size(), 4);
    }

    @Test
    public void tryPadToFour(){
        byte b = 0;
        ByteHelper byteHelper = new ByteHelper(b);
        byteHelper.pad();
        assertEquals(byteHelper.size(), 4);
    }

    @Test
    public void tryPadToFour1(){
        short s =0;
        ByteHelper byteHelper = new ByteHelper();
        byteHelper.addShort(s);
        byteHelper.pad();
        assertEquals(byteHelper.size(), 4);
    }
}
