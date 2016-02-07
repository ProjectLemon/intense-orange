package org.projectlemon.intenseorange.model.utilities.PDU;

import org.projectlemon.intenseorange.model.utilities.helpers.ByteHelper;

import java.io.InputStream;

/**
 * Created by Jenny on 2016-01-20.
 */
public class ChangeNickPDU extends PDU {
    private String nickname;
    public ChangeNickPDU(InputStream inputStream) {
        super();
    }

    public ChangeNickPDU(String nickname){
        super();
        this.nickname = nickname;
    }

    @Override
    public byte[] toByteArray() {
        byte[] nicknameAsBytes = nickname.getBytes();
        ByteHelper helper = new ByteHelper();
        helper.addByte(PDUIdentifier.CHANGENICK.value,(byte)nickname.length());
        helper.pad();
        helper.addByte(nicknameAsBytes);
        helper.pad();
        return helper.toByteArray();
    }
}
