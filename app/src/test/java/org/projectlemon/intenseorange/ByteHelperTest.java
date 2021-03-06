package app.src.test.java.org.projectlemon.intenseorange;


import org.junit.Test;
import org.projectlemon.intenseorange.model.utilities.NetworkVariables;
import org.projectlemon.intenseorange.model.utilities.helpers.ByteHelper;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;
/**
 * Created by Jenny on 2015-12-01.
 */
public class ByteHelperTest {

    @Test
    public void testAddByte(){
        byte b = 0;
        ByteHelper byteHelper = new ByteHelper(b);
        assertEquals(byteHelper.size(), 1);
    }

    @Test
    public void testAddShort(){
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
    public void testPad(){
        byte b = 0;
        ByteHelper byteHelper = new ByteHelper(b);
        byteHelper.pad();
        assertEquals(byteHelper.size(), 4);
    }

    @Test
    public void testPadToMoreThanFour(){
        short s =0;
        int i = 6;
        ByteHelper byteHelper = new ByteHelper();
        byteHelper.addShort(s);
        byteHelper.addInt(i);
        byteHelper.pad();
        assertEquals(byteHelper.size(), 8);
    }
    @Test
    public void tesCreateBuilder(){
        byte b = 3;
        ByteHelper byteHelper = new ByteHelper(b);
        byte [] builder = byteHelper.toByteArray();
        assertEquals(builder[0], 3);
    }

    @Test
    public void testPaddedSpaceIsZero(){
        byte b = 3;
        ByteHelper byteHelper = new ByteHelper(b);
        byteHelper.pad();
        byte [] builder = byteHelper.toByteArray();
        assertEquals(builder[2],0 );
    }

    @Test
    public void testForCorrectByteArraySize(){
        byte b = 3;
        ByteHelper byteHelper = new ByteHelper(b);
        byteHelper.pad();
        byte [] builder = byteHelper.toByteArray();
        assertEquals(builder.length,4);
    }

    @Test
    public void stripNetworkHeaderCorrectLength() {
        byte[] b = new byte[NetworkVariables.HEADER_SIZE+1];
        b = ByteHelper.stripNetworkHeader(b);
        assertEquals(b.length, 1);
    }

    @Test
    public void stripNetworkHeaderCorrectContent() throws Exception{
        byte[] a = new byte[NetworkVariables.HEADER_SIZE];
        byte[] b = new byte[] {1};

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(a);
        outputStream.write(b);

        byte[] c = ByteHelper.stripNetworkHeader(outputStream.toByteArray());
        assertEquals(c[0], 1);
    }

}
