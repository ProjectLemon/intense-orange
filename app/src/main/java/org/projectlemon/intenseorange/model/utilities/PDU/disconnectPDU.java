package org.projectlemon.intenseorange.model.utilities.PDU;

import org.projectlemon.intenseorange.model.utilities.helpers.ByteHelper;

import java.io.InputStream;

/**
 * This can be send from client to server or the otherway around. When this is send the
 * connection between the two will shut down.
 * Created by Jenny on 2016-01-20.
 */
public class DisconnectPDU extends PDU {
    public DisconnectPDU(InputStream inputStream) {
        super();
    }

    public DisconnectPDU(){
        super();
    }

    @Override
    public byte[] toByteArray() {
        ByteHelper helper = new ByteHelper();
        helper.addByte(PDUIdentifier.DISCONNECT.value);
        helper.pad();
        return helper.toByteArray();
    }
}
