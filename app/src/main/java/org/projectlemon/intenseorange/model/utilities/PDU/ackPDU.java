package org.projectlemon.intenseorange.model.utilities.PDU;

import org.eclipse.mat.parser.index.IndexWriter;
import org.projectlemon.intenseorange.model.utilities.helpers.ByteHelper;

import java.io.InputStream;

/**
 * Created by Jenny on 2016-01-20.
 */
public class AckPDU extends PDU {
    private PDUIdentifier identifierToSend;
    public AckPDU(InputStream inputStream) {
        super();
    }
    public AckPDU(PDUIdentifier identifierToSend){
      super();
        this.identifierToSend=identifierToSend;
    }

    @Override
    public byte[] toByteArray() {
        ByteHelper builder = new ByteHelper();
        builder.addByte(PDUIdentifier.ACK.value,identifierToSend.value);
        builder.pad();
        return builder.toByteArray();
    }
}
