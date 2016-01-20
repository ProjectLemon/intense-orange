package org.projectlemon.intenseorange.model.server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.nsd.WifiP2pDnsSdServiceInfo;

import org.projectlemon.intenseorange.controller.implementations.NetworkDevice;
import org.projectlemon.intenseorange.controller.interfaces.CallbackObject;
import org.projectlemon.intenseorange.model.utilities.PDU.PDU;
import org.projectlemon.intenseorange.model.utilities.helpers.CommonHelpers;
import org.projectlemon.intenseorange.model.utilities.helpers.ServerMessageHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Class: Server
 *
 * Purpose: The server class is an abstraction layer from the wifi direct group owner. Clients can
 *          connect to the server and communicate with it. It implements a "one to many" behaviour
 *          so a client can communicate with every other connected client.
 *
 * Created by Linus Lagerhjelm on 15-11-25.
 */
public class Server extends NetworkDevice implements Runnable {

    private String serverName;
    private Map<String, ClientThread> connectedClients = new HashMap<>();
    private ServerMessageHelper msgHelper = new ServerMessageHelper(this, null);
    private LinkedBlockingQueue<PDU> messageQueue = new LinkedBlockingQueue<>();
    private ConnectionListener connectionListener = new ConnectionListener(this);

    public Server(Context context, CallbackObject callable, String serverName) {
        super(context, callable);

        this.serverName = serverName;
        WifiBroadcastReceiver receiver = new WifiBroadcastReceiver(mManager, mChannel);
    }

    @Override
    public void sendData(byte[] data) {
        //TODO: implement
    }

    @Override
    public void receiveData(byte[] data) throws NullPointerException {
        //TODO: implement
    }

    @Override
    public void setNickname(String nickname) throws IllegalArgumentException {
        if(CommonHelpers.isNullOrEmpty(nickname)) {
            throw new IllegalArgumentException("No string value");
        }
        this.serverName = nickname;
    }

    /**
     * This method is blocking and should be invoked on a new thread. It fires up the server
     * to make it accept connections and initiates messaging functionality
     */
    @Override
    public void run() {
        Thread t1 = new Thread(msgHelper);
        Thread t2 = new Thread(connectionListener);
        t1.start();
        t2.start();

    }

    /**
     * This method will connect the specified client to the server in that sense that it will
     * register the client thread to receive messages from the server
     *
     * @param c The client to connect
     */
    public void connectClient(ClientThread c) {
        msgHelper.newClient(c);
        connectedClients.put(c.nickname, c);
    }

    /**
     * This method will disconnect the specified client from the server. The connection will be
     * closed and the client will no longer receive messages from the server
     *
     * @param c The client to disconnect
     */
    public void disconnectClient(ClientThread c) {
        msgHelper.removeClient(c);
        connectedClients.remove(c);
    }

    /**
     * Returns the currently connected clients as a Map with the clients nickname as key and
     * the connected client as value
     *
     * @return Map with connected clients
     */
    public Map<String, ClientThread> getConnectedClients() {
        return this.connectedClients;
    }

    /**
     * Returns the queue containing messages stored on the server that has not yet been distributed
     *
     * @return Thread safe queue containing unsent PDU:s
     */
    public LinkedBlockingQueue<PDU> getMessageQueue() {
        return messageQueue;
    }

    /**
     * Intellij generated method
     *
     * @param o object to compare to this
     * @return true if they are the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Server server = (Server) o;

        if (serverName != null ? !serverName.equals(server.serverName) : server.serverName != null)
            return false;
        if (connectedClients != null ? !connectedClients.equals(server.connectedClients) : server.connectedClients != null)
            return false;
        if (msgHelper != null ? !msgHelper.equals(server.msgHelper) : server.msgHelper != null)
            return false;
        if (messageQueue != null ? !messageQueue.equals(server.messageQueue) : server.messageQueue != null)
            return false;
        return !(connectionListener != null ? !connectionListener.equals(server.connectionListener) : server.connectionListener != null);

    }

    /**
     * Intellij generated method
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int result = serverName != null ? serverName.hashCode() : 0;
        result = 31 * result + (connectedClients != null ? connectedClients.hashCode() : 0);
        result = 31 * result + (msgHelper != null ? msgHelper.hashCode() : 0);
        result = 31 * result + (messageQueue != null ? messageQueue.hashCode() : 0);
        result = 31 * result + (connectionListener != null ? connectionListener.hashCode() : 0);
        return result;
    }

    /**
     * Class: WifiBroadcastReceiver
     *
     * Purpose: WifiBroadcastReceiver is a broadcast receiver that will respond do system messages
     * related to wifi direct. It focuses on state change and peers change in order to
     * discover if wifi direct is available and if there are any incoming connections.
     */
    public class WifiBroadcastReceiver extends BroadcastReceiver {
        WifiP2pManager mManager;
        WifiP2pManager.Channel mChannel;

        public WifiBroadcastReceiver(WifiP2pManager mManager, WifiP2pManager.Channel mChannel) {
            this.mManager = mManager;
            this.mChannel = mChannel;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
                handleStateChanged(intent);
            }
        }

        /**
         * This method will try to resolve why the wifip2p-state changed. If wifip2p was enbled
         * it will start broadcast a local service telling everyone around that they are
         * available to connect to.
         *
         * @param intent the intent that caused this method to be called
         */
        private void handleStateChanged(Intent intent) {
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if( !(state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) ) {
                callback.onError(state);
            } else {
                Map<String, String> serverInfo = new HashMap<>();
                serverInfo.put("serverName", serverName);

                WifiP2pDnsSdServiceInfo serviceInfo;
                serviceInfo = WifiP2pDnsSdServiceInfo.newInstance(serverName,
                        "._intense-orange-server", serverInfo);

                mManager.addLocalService(mChannel, serviceInfo, new WifiP2pManager.ActionListener(){
                    @Override
                    public void onSuccess() {  }

                    @Override
                    public void onFailure(int reason) {
                        callback.onError(reason);
                    }
                });


            }
        }

    }
}

