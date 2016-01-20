package org.projectlemon.intenseorange.model.client;

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

    }
}
