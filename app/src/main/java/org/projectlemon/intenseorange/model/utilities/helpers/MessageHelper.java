package org.projectlemon.intenseorange.model.utilities.helpers;

import org.projectlemon.intenseorange.model.Client;
import org.projectlemon.intenseorange.model.server.Server;
import org.projectlemon.intenseorange.model.utilities.PDU.PDU;
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
        /**
         * If the message helper is used by the server:
         *  update the list of connected clients
         *  as long as the thread is running:
         *   if there are a new message in the message queue:
         *    send the message to all connected clients
         */
        if(role == Role.SERVER){
            connectedClients = server.getConnectedClients();
            while(true) {
                if(server.getMessageQueue().size() > 0) {
                    for(PDU msg:server.getMessageQueue()) {
                        for(Client c:connectedClients) {
                            c.sendMessage(msg.toByteArray());
                        }
                    }
                }
            }
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
