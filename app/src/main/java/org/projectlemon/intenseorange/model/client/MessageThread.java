package org.projectlemon.intenseorange.model.client;

import org.projectlemon.intenseorange.model.utilities.PDU.PDU;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by linuslagerhjelm on 16-01-20.
 */
public class MessageThread implements Runnable {
    private Socket server;

    public MessageThread(Socket server) {
        this.server = server;
    }

    @Override
    public void run() {
        try {
            while(true) {
                PDU pdu = PDU.fromInputStream(server.getInputStream());
                //TODO: Handle receive pdu type
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendData(byte[] msg) throws IOException{
        OutputStream out = server.getOutputStream();
        out.write(msg);
    }
}
