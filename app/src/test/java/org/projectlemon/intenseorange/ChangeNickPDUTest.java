package org.projectlemon.intenseorange;

import org.junit.Test;
import org.projectlemon.intenseorange.model.utilities.PDU.ChangeNickPDU;
import org.projectlemon.intenseorange.model.utilities.PDU.PDUIdentifier;
import static junit.framework.Assert.assertEquals;

/**
 * Sends if a client wants to change it's nickname.
 * Created by Jenny on 2016-02-03.
 */
public class ChangeNickPDUTest {
    String nickname = "J3nnyK?n£rt";
    byte[] nicknameAsByte = nickname.getBytes();
    int length = nicknameAsByte.length;
    ChangeNickPDU pdu = new ChangeNickPDU(nickname);
    byte[] pduByteArray = pdu.toByteArray();

    @Test
    public void testIfCorrectIdentifier(){
        assertEquals(PDUIdentifier.CHANGENICK.value, pduByteArray[0]);
    }

    @Test
    public void testIfCorrectNickLength(){
        assertEquals((byte)length,pduByteArray[1]);
    }

    @Test
    public void testIfPaddedCorrect(){
        assertEquals((byte)0, pduByteArray[2]);
        assertEquals((byte)0, pduByteArray[3]);
    }

    @Test
    public void testIfCorrectNickname(){
        byte[] nickBytes = new byte[pduByteArray[1]];
        System.arraycopy(pduByteArray, 4, nickBytes, 0, length);
        assertEquals(nickname, new String(nickBytes));
    }
    @Test
    public void testIfCorrectLength(){
        assertEquals(16, pduByteArray.length);
    }
}
