package org.projectlemon.intenseorange;


import org.junit.Test;
import org.projectlemon.intenseorange.model.utilities.PDU.MessagePDU;
import org.projectlemon.intenseorange.model.utilities.PDU.PDU;
import org.projectlemon.intenseorange.model.utilities.PDU.PDUIdentifier;

import java.util.Date;
import static junit.framework.Assert.*;


/**
 * Created by Jenny on 2016-02-07.
 */
public class MessagePDUTest {
    private final String nickname = "J3nnyK1n£r!t";
    private final Date timestamp = new Date(45568221);
    private final String message = "Jag är bäst i h3l)a v?rld#n på An@roid";
    private MessagePDU pdu = new MessagePDU(nickname,timestamp,message);
    private byte[] pduAsBytes = pdu.toByteArray();

    @Test
    public void testIfRightIdentifier(){
        assertEquals(PDUIdentifier.MESSAGE.value, pduAsBytes[0]);
    }

    @Test
    public void testIfCorrectMessageLength(){
        int messageLength = message.getBytes().length;
        assertEquals((byte)messageLength,pduAsBytes[1]);
    }

    @Test
    public void testIfCorrectNicknameLength(){
        int nicknamelength = nickname.getBytes().length;
        assertEquals((byte)nicknamelength,pduAsBytes[2]);
    }
    @Test
    public void testIfPaddedSpot(){
        assertEquals((byte)0,pduAsBytes[3]);
    }

    @Test
    public void testIfCorrectTimeStamp(){
            Date actualDate = new Date(PDU.byteArrayToLong(pduAsBytes, 4, 8) * 1000);
            Date cmpDate = new Date((timestamp.getTime() / 1000) * 1000);
            assertEquals(cmpDate, actualDate);
    }
    @Test
    public void testIfCorrectNickname(){
        byte[] nickBytes = new byte[pduAsBytes[2]];
        System.arraycopy(pduAsBytes, 9, nickBytes, 0, nickBytes.length);
        assertEquals(nickname, new String(nickBytes));
    }
    @Test
    public void testIfCorrectMessage(){

    }
}
