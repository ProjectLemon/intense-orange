package org.projectlemon.intenseorange.model.utilities.PDU;

import org.projectlemon.intenseorange.model.utilities.helpers.ByteHelper;

import java.io.InputStream;
import java.util.Date;

/**
 * Created by Jenny on 2016-01-20.
 */
public class MessagePDU extends PDU {
    String nickname;
    String message;
    Date timestamp;

    public MessagePDU(InputStream inputStream) {
        super();
    }

    public MessagePDU(String nickname, Date timestamp, String message){
        this.nickname=nickname;
        this.timestamp = new Date((timestamp.getTime()/1000)*1000);
        this.message = message;
    }

    @Override
    public byte[] toByteArray() {
        ByteHelper helper  = new ByteHelper();
        byte[] nicknameAsBytes = nickname.getBytes();
        byte[] messageAsBytes = message.getBytes();
        helper.addByte(PDUIdentifier.MESSAGE.value);
        helper.addByte((byte) messageAsBytes.length);
        helper.addByte((byte) nicknameAsBytes.length);
        helper.pad();
        helper.addInt((int)(timestamp.getTime()/1000));
        helper.addByte(nicknameAsBytes);
        helper.addByte(messageAsBytes);
        helper.pad();
        return helper.toByteArray();
    }
}
