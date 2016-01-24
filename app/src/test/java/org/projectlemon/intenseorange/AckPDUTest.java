package org.projectlemon.intenseorange;

import org.junit.Test;
import org.projectlemon.intenseorange.model.utilities.PDU.AckPDU;
import org.projectlemon.intenseorange.model.utilities.PDU.PDUIdentifier;

import static junit.framework.Assert.*;

/**
 * Created by Jenny on 2016-01-24.
 */
public class AckPDUTest {
    private PDUIdentifier message = PDUIdentifier.MESSAGE;
    private AckPDU PDU = new AckPDU(message);

    @Test
    public void testIfRightIdentifier(){
        byte[] bytePDU = PDU.toByteArray();
        assertEquals(PDUIdentifier.ACK.value, bytePDU[0]);
    }
    @Test
    public void testIfRightAckIdentifier(){
        byte[] bytePDU = PDU.toByteArray();
        assertEquals(PDUIdentifier.MESSAGE.value, bytePDU[1]);
    }
    @Test
    public void testRightSize(){
        byte[] bytePDU = PDU.toByteArray();
        assertEquals(4,bytePDU.length);
    }
    @Test
    public void shouldHavePaddedHeader(){
        byte[] bytePDU = PDU.toByteArray();
        assertEquals((byte)0, bytePDU[2]);
        assertEquals((byte)0, bytePDU[3]);
    }
    @Test
    public void equalWhenReadFromStream(){
        fail("Not yet implemented");
    }
}

