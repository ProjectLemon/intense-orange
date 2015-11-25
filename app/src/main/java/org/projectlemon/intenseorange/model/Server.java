package org.projectlemon.intenseorange.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linuslagerhjelm on 15-11-25.
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
}
