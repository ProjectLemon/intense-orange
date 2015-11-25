package org.projectlemon.intenseorange.model.utilities.helpers;

import java.util.ArrayList;

/**
 * Class: ByteHelper
 * Purpose: Class to build byte sequences for information to be send between units in the peer
 * network.
 * Created by linuslagerhjelm on 15-11-25.
 */
public class ByteHelper {

   private ArrayList<Byte> arrayOfBytes = new ArrayList<>();

    public ByteHelper(byte... arrayOfBytes) {
        addByte(arrayOfBytes);
    }

    /**
     * Take bytes as an input parameter and stores it in the arrayList arrayOfBytes.
     * @param arrayOfBytes
     * @return
     */

    public byte[] addByte(byte... arrayOfBytes){
        for (byte b : arrayOfBytes) {
            this.arrayOfBytes.add(b);
        }
        return arrayOfBytes;
    }
}
