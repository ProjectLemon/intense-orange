package org.projectlemon.intenseorange.model.utilities.PDU;

import java.io.InputStream;

/**
 * Created by Jenny on 2016-01-20.
 */
public class messagePDU extends PDU {
    public messagePDU(InputStream inputStream) {
        super();
    }

    @Override
    public byte[] toByteArray() {
        return new byte[0];
    }
}
