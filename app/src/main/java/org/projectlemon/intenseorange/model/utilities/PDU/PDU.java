package org.projectlemon.intenseorange.model.utilities.PDU;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by linuslagerhjelm on 16-01-01.
 */
public abstract class PDU{
    //TODO: implement this class with methods and other cool stuff

    public static PDU fromInputStream(InputStream inputStream) throws IOException,
                                                                    UnsupportedOperationException {
        PDU pdu = null;
        int identifyer;
            identifyer = inputStream.read();

        switch(identifyer){
            case 0:
                return new connectPDU(inputStream);
            case 1:
                return new disconnectPDU(inputStream);
            case 2:
                return new AckPDU(inputStream);
            case 3:
                return new changeNickPDU(inputStream);
            case 4:
                return new messagePDU(inputStream);
            case 5:
                return new errorPDU(inputStream);
        }
        //TODO: Implement
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public abstract byte[] toByteArray();
}
