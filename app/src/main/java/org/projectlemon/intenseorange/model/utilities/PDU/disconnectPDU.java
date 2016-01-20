package org.projectlemon.intenseorange.model.utilities.PDU;

import java.io.InputStream;

/**
 * Created by Jenny on 2016-01-20.
 */
public class disconnectPDU extends PDU {
    public disconnectPDU(InputStream inputStream) {
        super();
    }

    @Override
    public byte[] toByteArray() {
        // TODO: 2016-01-20 implement
        return new byte[0];
    }
}
