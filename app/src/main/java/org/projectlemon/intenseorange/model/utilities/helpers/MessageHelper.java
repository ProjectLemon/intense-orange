package org.projectlemon.intenseorange.model.utilities.helpers;

import org.projectlemon.intenseorange.model.Client;
import org.projectlemon.intenseorange.model.server.Server;
import org.projectlemon.intenseorange.model.utilities.Role;

import java.util.List;

/**
 * Class: Message helper
 * Purpose: This class contains methods that will make it easier to send messages on the network
 *
 * Created by Linus Lagerhjelm on 15-11-25.
 */
public class MessageHelper implements Runnable{
    Role role;
    Server server;
    Client client;
    List<Client> connectedClients;

    public MessageHelper(Role r, Server s, Client c) {
        this.role = r;
        this.server = s;
        this.client = c;
    }

    @Override
    public void run() {
        if(role == Role.SERVER){
            connectedClients = server.getConnectedClients();
        } else {

        }
    }

    public void newClient(Client c) {
        connectedClients.add(c);
    }
    public void removeClient(Client c) {
        connectedClients.remove(c);
    }
}
