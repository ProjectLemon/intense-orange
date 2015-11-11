package org.projectlemon.intenseorange.model.helpers;

import org.projectlemon.intenseorange.model.wifiPackage.WifiPackage;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Jenny on 2015-11-11.
 * Contain queue with messages to send and a list of peers to send messages to.
 * As long as the program runs and the queue contains messages it should sen these to every peer.
 */
public class MessageHelper {
    private Queue<WifiPackage> messages = new LinkedBlockingQueue<>();

    public void addMessageToQueue(WifiPackage packageMessage){
        messages.add(packageMessage);
    }
}
