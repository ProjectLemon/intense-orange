package org.projectlemon.intenseorange.model.utilities.PDU;

/**
 * Enum for identifying which PDU the device has received.
 * Created by Jenny on 2016-01-20.
 */
public enum PDUIdentifier {
    CONNECT(0),
    DISCONNECT(1),
    ACK(2),
    CHANGENICK(3),
    MESSAGE(4),
    ERROR(5);

    public final byte value;
    PDUIdentifier(int value) {
        this.value = (byte)value;
    }

}
