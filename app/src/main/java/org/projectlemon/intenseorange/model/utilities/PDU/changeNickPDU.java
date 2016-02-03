package org.projectlemon.intenseorange.model.utilities.PDU;

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
        return new byte[0];
    }
}
