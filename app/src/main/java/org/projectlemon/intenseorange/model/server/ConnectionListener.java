package org.projectlemon.intenseorange.model.server;

import org.projectlemon.intenseorange.model.utilities.NetworkVariables;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by linuslagerhjelm on 16-01-18.
 */
public class ConnectionListener implements Runnable{
    Server server;

    public ConnectionListener(Server s){
        this.server = s;
    }
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(NetworkVariables.PORT);
            while (true){
                Socket socket = serverSocket.accept();
                if(socket.isConnected()){
                    Thread t = new Thread(new ClientThread(server, socket));
                    t.start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
