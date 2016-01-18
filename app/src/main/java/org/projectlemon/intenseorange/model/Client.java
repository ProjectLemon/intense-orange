package org.projectlemon.intenseorange.model;

/**
 * Created by Jenny on 2015-11-11.
 */
public class Client implements Runnable{
    private int id = 0;



    public Client(int id){
        this.id = id;
    }
    public int getKey(){
        return id;
    }

    @Override
    public void run() {

    }

    public void sendMessage(byte[] msg) {

    }
    @Override
     public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
