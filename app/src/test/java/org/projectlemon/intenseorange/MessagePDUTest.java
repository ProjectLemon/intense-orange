package org.projectlemon.intenseorange;

import android.os.Message;

import org.junit.Test;
import org.projectlemon.intenseorange.model.utilities.PDU.MessagePDU;
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
        assertEquals((byte)message.length(),pduAsBytes[1]);
    }

    @Test
    public void testIfCorrectNicknameLength(){
        assertEquals((byte)message.length(),pduAsBytes[2]);
    }
    @Test
    public void testIfPaddedSpot(){
        assertEquals((byte)message.length(),pduAsBytes[3]);
    }
    @Test
    public void testIfCorrectTimeStamp(){

    }
}
