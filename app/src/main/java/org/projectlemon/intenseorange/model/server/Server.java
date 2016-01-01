package org.projectlemon.intenseorange.model.server;

import org.projectlemon.intenseorange.model.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * Class: Server
 * Purpose: The server class is an abstraction layer from the wifi direct group owner. Clients can
 *          connect to the server and communicate with it. It implements a "one to many" behaviour
 *          so a client can communicate with every other connected client.
 *
 * Created by Linus Lagerhjelm on 15-11-25.
 */
public class Server implements Runnable {
    private ServerSocket serverSocket;
    private List<Client> connectedClients = new ArrayList<>();

    public Server() {
        try {
            serverSocket = new ServerSocket();
        } catch (IOException e) {}
    }

    @Override
    public void run() {

    }

    public void connectClient(Client c) {
        connectedClients.add(c);
    }
    public List<Client> getConnectedClients() {
        return this.connectedClients;
    }
}
