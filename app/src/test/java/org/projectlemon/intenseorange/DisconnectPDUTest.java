package org.projectlemon.intenseorange;

import org.junit.Test;
import org.projectlemon.intenseorange.model.utilities.PDU.DisconnectPDU;
import org.projectlemon.intenseorange.model.utilities.PDU.PDUIdentifier;

import static junit.framework.Assert.assertEquals;

/**
 * Testclass for DisconnectPDU
 * Created by Jenny on 2016-02-03.
 */
public class DisconnectPDUTest {

    DisconnectPDU pdu = new DisconnectPDU();

    @Test
    public void testIfRightIdentifier(){
        byte [] bytePDU = pdu.toByteArray();
        assertEquals(PDUIdentifier.DISCONNECT.value, bytePDU[0]);
    }

    @Test
    public void testIfPaddedCorrect(){
        byte [] bytePDU = pdu.toByteArray();
        assertEquals((byte)0,bytePDU[1]);
        assertEquals((byte)0,bytePDU[2]);
        assertEquals((byte)0,bytePDU[3]);
    }

    @Test
    public void testIfCorrectLength(){
        byte [] bytePDU = pdu.toByteArray();
        assertEquals(4,bytePDU.length);
    }
}

