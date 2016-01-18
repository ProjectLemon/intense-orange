package org.projectlemon.intenseorange.model.server;

import org.projectlemon.intenseorange.model.utilities.PDU.PDU;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by linuslagerhjelm on 16-01-18.
 */
public class ClientThread implements Runnable{
    Server server;
    Socket socket;

    public ClientThread(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        server.connectClient(this);
        try{
            while (true) {
                PDU pdu = PDU.fromInputStream(socket.getInputStream());
            }
        } catch(Exception e) { e.printStackTrace(); }
    }

    public void sendMessage(PDU msg) {
        try {
            OutputStream out = socket.getOutputStream();
            out.write(msg.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
