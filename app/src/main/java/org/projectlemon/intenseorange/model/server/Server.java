package org.projectlemon.intenseorange.model.server;

import org.projectlemon.intenseorange.model.Client;
import org.projectlemon.intenseorange.model.utilities.NetworkVariables;
import org.projectlemon.intenseorange.model.utilities.PDU.PDU;
import org.projectlemon.intenseorange.model.utilities.Role;
import org.projectlemon.intenseorange.model.utilities.helpers.MessageHelper;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Class: Server
 * Purpose: The server class is an abstraction layer from the wifi direct group owner. Clients can
 *          connect to the server and communicate with it. It implements a "one to many" behaviour
 *          so a client can communicate with every other connected client.
 *
 * Created by Linus Lagerhjelm on 15-11-25.
 */
public class Server implements Runnable {
    private MessageHelper msgHelper = new MessageHelper(Role.SERVER, this, null);
    private List<ClientThread> connectedClients = new ArrayList<>();
    private LinkedBlockingQueue<PDU> messageQueue = new LinkedBlockingQueue<>();
    private ConnectionListener connectionListener = new ConnectionListener(this);

    public Server() {

    }

    @Override
    public void run() {
        Thread t1 = new Thread(msgHelper);
        Thread t2 = new Thread(connectionListener);
        t1.start();
        t2.start();

    }

    public void connectClient(ClientThread c) {
        msgHelper.newClient(c);
        connectedClients.add(c);
    }
    public void disconnectClient(ClientThread c) {
        msgHelper.removeClient(c);
        connectedClients.remove(c);
    }
    public List<ClientThread> getConnectedClients() {
        return this.connectedClients;
    }
    public LinkedBlockingQueue<PDU> getMessageQueue() {
        return messageQueue;
    }
}
