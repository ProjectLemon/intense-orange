package org.projectlemon.intenseorange.model.utilities.PDU;

import java.io.InputStream;

/**
 * Transport if a client wish to connect to a server. 
 * Created by Jenny on 2016-01-20.
 */
public class ConnectPDU extends PDU {
    public ConnectPDU(InputStream inputStream) {
        super();
    }

    @Override
    public byte[] toByteArray() {
        return new byte[0];
        // TODO: 2016-01-20 Implement  
    }
}
