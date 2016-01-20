package org.projectlemon.intenseorange.model.utilities.helpers;

import org.projectlemon.intenseorange.model.client.Client;
import org.projectlemon.intenseorange.model.server.ClientThread;
import org.projectlemon.intenseorange.model.server.Server;
import org.projectlemon.intenseorange.model.utilities.PDU.PDU;

import java.util.Map;

/**
 * Class: Message helper
 * Purpose: This class contains methods that will make it easier to send messages on the network
 *
 * Created by Linus Lagerhjelm on 15-11-25.
 */
public class ServerMessageHelper implements Runnable{
    Server server;
    Client client;
    Map<String, ClientThread> connectedClients;

    public ServerMessageHelper(Server s, Client c) {
        this.server = s;
        this.client = c;
    }


    @Override
    public void run() {
        /**
         * If the message helper is used by the server:
         *  update the list of connected clients
         *  as long as the thread is running:
         *   if there are new messages in the message queue:
         *    send the message to all connected clients
         */
        connectedClients = server.getConnectedClients();
        while(true) {
            if(server.getMessageQueue().size() > 0) {
                for(PDU msg:server.getMessageQueue()) {
                    for(Map.Entry<String, ClientThread> c : connectedClients.entrySet()) {
                        c.getValue().sendMessage(msg);
                    }
                }
            }
        }
    }

    public void newClient(ClientThread c) {
        connectedClients.put(c.getNickname(), c);
    }
    public void removeClient(ClientThread c) {
        connectedClients.remove(c);
    }
}
