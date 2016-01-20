package org.projectlemon.intenseorange.model.client;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;

import org.projectlemon.intenseorange.controller.implementations.NetworkDevice;
import org.projectlemon.intenseorange.controller.interfaces.CallbackObject;
import org.projectlemon.intenseorange.model.utilities.NetworkVariables;
import org.projectlemon.intenseorange.model.utilities.helpers.CommonHelpers;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jenny on 2015-11-11.
 */
public class Client extends NetworkDevice implements Runnable {
    String nickname;
    private InetAddress serverAddress;
    private Socket server;
    private Map<String, WifiP2pDevice> availableServers = new HashMap<>();
    private MessageThread messageThread = new MessageThread(server);
    public WifiBroadcastReceiver receiver;


    public Client(Context context, CallbackObject callable) {
        super(context, callable);
        receiver = new WifiBroadcastReceiver(mManager, mChannel);
    }

    @Override
    public void run() {
        Thread t1 = new Thread(messageThread);
        t1.start();
    }

    @Override
    public void sendData(byte[] data) throws IOException {
        messageThread.sendData(data);
    }

    @Override
    public void receiveData(byte[] data) throws NullPointerException {

    }

    /**
     * This method will try to connect the client to the specified server
     *
     * @param nickname The name of the server to connect to
     * @throws IllegalArgumentException If no valid string was provided
     * @throws IllegalStateException If requested server could not be verified
     *
     * Note: onError()      is called if wifip2p failed to connect
     * Note: onException()  is called if the socket connection failed
     */
    public void connectToDevice(String nickname) throws IllegalArgumentException,
                                                        IllegalStateException {
        if(CommonHelpers.isNullOrEmpty(nickname)) {
            throw new IllegalArgumentException("No string provided");
        }
        if(!availableServers.containsKey(nickname)) {
            throw new IllegalArgumentException("Server not available");
        }

        WifiP2pDevice serverUnit = availableServers.get(nickname);
        WifiP2pConfig config = new WifiP2pConfig();
        config.deviceAddress = serverUnit.deviceAddress;
        mManager.connect(mChannel, config, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                try{
                    server = new Socket(serverAddress.getHostName(), NetworkVariables.PORT);
                } catch (IOException e) {
                    callback.onException(e);
                }
            }

            @Override
            public void onFailure(int reason) {
                callback.onError(reason);
            }
        });

    }

    public Map<String, WifiP2pDevice> getAvailableServers() {
        return this.availableServers;
    }

    @Override
    public void setNickname(String nickname) {
        if(CommonHelpers.isNullOrEmpty(nickname)) {
            throw new IllegalArgumentException("No string value");
        }
        this.nickname = nickname;
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

        Client client = (Client) o;

        if (nickname != null ? !nickname.equals(client.nickname) : client.nickname != null)
            return false;
        if (serverAddress != null ? !serverAddress.equals(client.serverAddress) : client.serverAddress != null)
            return false;
        if (server != null ? !server.equals(client.server) : client.server != null) return false;
        return !(availableServers != null ? !availableServers.equals(client.availableServers) : client.availableServers != null);

    }

    /**
     * Intellij generated method
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int result = nickname != null ? nickname.hashCode() : 0;
        result = 31 * result + (serverAddress != null ? serverAddress.hashCode() : 0);
        result = 31 * result + (server != null ? server.hashCode() : 0);
        result = 31 * result + (availableServers != null ? availableServers.hashCode() : 0);
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
                WifiP2pManager.DnsSdTxtRecordListener txtListener;
                txtListener = new WifiP2pManager.DnsSdTxtRecordListener() {
                    @Override
                    public void onDnsSdTxtRecordAvailable(String fullDomainName,
                                                          Map<String, String> txtRecordMap,
                                                          WifiP2pDevice srcDevice) {
                        availableServers.put(fullDomainName, srcDevice);
                    }
                };
            }
        }

    }
}
