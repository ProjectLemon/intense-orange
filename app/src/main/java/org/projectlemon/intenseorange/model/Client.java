package org.projectlemon.intenseorange.model;

/**
 * Created by Jenny on 2015-11-11.
 */
public class Client implements Runnable{
    private int id =0;

    public Client(){

    }
    public int getKey(){
        return id;
    }

    @Override
    public void run() {

    }

    public void sendMessage(byte[] msg) {

    }
}
