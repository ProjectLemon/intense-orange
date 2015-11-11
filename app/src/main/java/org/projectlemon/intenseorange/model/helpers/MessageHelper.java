package org.projectlemon.intenseorange.model.helpers;

import android.net.wifi.p2p.WifiP2pDevice;

import org.projectlemon.intenseorange.model.Client;
import org.projectlemon.intenseorange.model.utilities.clientKey;
import org.projectlemon.intenseorange.model.wifiPackage.WifiPackage;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Jenny on 2015-11-11.
 * Contain queue with messages to send and a list of peers to send messages to.
 * As long as the program runs and the queue contains messages it should sen these to every peer.
 */
public class MessageHelper {
    private Queue<WifiPackage> messages = new LinkedBlockingQueue<>();
    private HashMap<Integer, Client> connectedPeers = new HashMap<>();

    public void addMessageToQueue(WifiPackage packageMessage) {
        messages.add(packageMessage);
    }

    public void addClientToHashMap(Client peer) {
        connectedPeers.put(peer.getKey(), peer);
    }

    /*
        Should send messages to all connected clients.
         TODO: We need to fix how to send this. Which class will handle to send messages and
               how will they be send. And how will I know where to send the message. 
     */
    public void senMessagesToPeers() {
        while (!messages.isEmpty()) {
            for (Client peer : connectedPeers.values()) {
                Client client = peer;
            }
        }
    }
}


