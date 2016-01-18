package org.projectlemon.intenseorange.model;

import org.projectlemon.intenseorange.model.utilities.NetworkVariables;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Jenny on 2015-11-11.
 */
public class Client implements Runnable{
    private int id = 0;
    private InetAddress serverAddress;
    private Socket server;



    public Client(int id, InetAddress serverAddress) throws IOException {
        this.id = id;
        this.serverAddress = serverAddress;
        server = new Socket(serverAddress.getHostName(), NetworkVariables.PORT);
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
