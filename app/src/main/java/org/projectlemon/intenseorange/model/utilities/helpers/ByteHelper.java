package org.projectlemon.intenseorange.model.utilities.helpers;

import java.util.ArrayList;

/**
 * Class: ByteHelper
 * Purpose: Class to build byte sequences for information to be send between units in the peer
 *          network.
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
     * @param arrayOfBytes the bytes to add to the sequence
     * @return The new byte sequence
     */

    public byte[] addByte(byte... arrayOfBytes){
        for (byte b : arrayOfBytes) {
            this.arrayOfBytes.add(b);
        }
        return arrayOfBytes;
    }

    /**
     * Take a short as input and modifies it to bytes.
     * @param s the short type value to add to the sequence
     * @return The new byte sequence
     */
    public ArrayList<Byte> addShort(Short s){
        this.arrayOfBytes.add((byte) ((s >> 8) & 0xff));
        this.arrayOfBytes.add((byte) (s & 0xff));
        return arrayOfBytes;
    }

    /**
     * Take an int as input and modifies it to bytes.

     * @param i the int type value to add to the sequence
     * @return The new byte sequence
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
     * @return the byte sequence as a byte array;
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
     * @return the new byte sequence
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
     * Get the size of the byte sequence
     * @return The length of the byte sequence
     */
    public int size() {
        return arrayOfBytes.size();
    }

}
