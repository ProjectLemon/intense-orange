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
    private boolean dividedByFour;

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

    /**
     * Take a short as input and modifies it to bytes.
     * @param s
     * @return
     */
    public ArrayList<Byte> addShort(Short s){
        this.arrayOfBytes.add((byte) ((s >> 8) & 0xff));
        this.arrayOfBytes.add((byte) (s & 0xff));
        return arrayOfBytes;
    }

    /**
     * Take an int as input and modifies it to bytes.
     * @param i
     * @return
     */
    public ArrayList<Byte> addInt(int i){
        this.arrayOfBytes.add((byte) ((i >> 24) & 0xff));
        this.arrayOfBytes.add((byte) ((i >> 16) & 0xff));
        this.arrayOfBytes.add((byte) ((i >> 8) & 0xff));
        this.arrayOfBytes.add((byte) (i & 0xff));
        return arrayOfBytes;
    }

    /**
     * This is a builder. It builds a byte array by the pieces in the arraylist.
     * @return result;
     */
    public byte[] toByteArray() {
        byte[] result = new byte[arrayOfBytes.size()];
        for (int i = 0; i < arrayOfBytes.size(); i++) {
            result[i] = arrayOfBytes.get(i);
        }
        return result;
    }

    /**
     * Pad the sequence with byte zeros so it's always divisible by four.
     * @return
     */
    public ArrayList<Byte> pad(){

        dividedByFour = false;

        while(!dividedByFour){
            if(arrayOfBytes.size()%4!=0) {
                arrayOfBytes.add((byte)0);
            }
            else{
                dividedByFour = true;
            }
        }
        return arrayOfBytes;
    }

    /**
     * Returns the size of the array.
     * @return
     */
    public int size() {
        return arrayOfBytes.size();
    }

}
