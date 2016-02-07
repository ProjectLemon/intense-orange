package org.projectlemon.intenseorange.model.utilities.PDU;

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
        this.timestamp = timestamp;
        this.message = message;
    }

    @Override
    public byte[] toByteArray() {
        return new byte[0];
    }
}
