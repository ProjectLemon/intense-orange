package org.projectlemon.intenseorange.controller.implementations;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Looper;
import android.widget.Toast;

import org.projectlemon.intenseorange.controller.interfaces.CallbackObject;
import org.projectlemon.intenseorange.controller.interfaces.NerworkControllerInterface;
import org.projectlemon.intenseorange.model.Client;
import org.projectlemon.intenseorange.model.server.Server;
import org.projectlemon.intenseorange.model.network.WifiDirectReciever;
import org.projectlemon.intenseorange.model.utilities.Role;
import org.projectlemon.intenseorange.model.utilities.exceptions.UnableToConnectException;

import java.util.ArrayList;
import java.util.List;

//TODO: Handle device no wifi

/**
 * Class: NetworkController
 * Purpose: Implements the NetworkController interface. It abstracts away the code
 *          for using the network api. It takes care of initializing a wifi direct connection,
 *          discover, connecting to and communicate with other devices
 *
 * Created by Jenny on 2015-11-11.
 */
public class NetworkController implements NerworkControllerInterface {
    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    private BroadcastReceiver wifiReceiver;
    private IntentFilter intentFilter;
    private Context context;
    private Role role;
    private CallbackObject callbackFunction;
    private List<WifiP2pDevice> peerList = new ArrayList();
    public Server server;
    public Client client;

    /**
     * Creates a new NetworkController to be used for network communication between devices
     * @param context The activity that will communicate with the network
     * @param role States weather the device is the server or a client
     * @param function The function that will be called when data is sent to this device
     */
    public NetworkController (Context context, Role role, CallbackObject function ) {
        this.context = context;
        this.role = role;
        this.callbackFunction = function;

        WifiP2pManager.PeerListListener peerListener = createPeerListener();
        mManager = (WifiP2pManager)context.getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(context, Looper.getMainLooper(), null);
        wifiReceiver = new WifiDirectReciever(mManager, mChannel, context, peerListener, this);
        setupIntentFilter();
    }


    /**
     * Registers this object as a broadcast receiver for wifi broadcasts within the system
     */
    @Override
    public void onResume() {
        context.registerReceiver(wifiReceiver, intentFilter);
    }

    /**
     * Unregister this object as a broadcast receiver for wifi broadcasts within the system
     */
    @Override
    public void onPause() {
        context.unregisterReceiver(wifiReceiver);
    }

    /**
     * Upon calling this method. The device will open a wifi direct connection with surrounding
     * devices that is part of the game. Depending on it's role, the device will act as a server
     * or as a client that connects to a group.
     */
    @Override
    public void start() throws UnableToConnectException {
        WifiManager wifi = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        if (!wifi.isWifiEnabled()){
            wifi.setWifiEnabled(true);
        }

        try{
            mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
                @Override
                public void onSuccess() {
                    Toast.makeText(context, "Searching... ", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(int reason) {
                    throw new RuntimeException("wifi direct not supported. Code: "+reason);
                }
            });
        } catch(RuntimeException e) {
            throw new UnableToConnectException(e.getMessage());
        }
    }

    @Override
    public void pause() {
        //TODO: Implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Called to close the connection. This method should only be called when everything is
     * finished and the connection not is to be opened since it would cause a renegotiation
     * of roles and connection establishment. Which is time and battery consuming.
     *
     * Use pause instead to save battery
     */
    @Override
    public void close() {
        mManager.cancelConnect(mChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(context, "Connection closed", Toast.LENGTH_SHORT);
            }

            @Override
            public void onFailure(int reason) {

            }
        });
    }

    /**
     * This method will send the specified data to the connected devices
     * @param data the data to send
     */
    @Override
    public void sendData(byte[] data) {
        //TODO: Implement
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * This method will call the handleData()-method with the received data
     * @param data the data to receive
     */
    @Override
    public void receiveData(byte[] data) throws NullPointerException {
        callbackFunction.handleData(data);
    }

    private void connectToGroupOwner() {
        //Collection<WifiP2pDevice> devices = peers.getDeviceList();
        WifiP2pConfig config = new WifiP2pConfig();
        Toast.makeText(context, "Trying to connect to group", Toast.LENGTH_SHORT).show();
        for (WifiP2pDevice device: peerList) {
            if(device.isGroupOwner()) {
                config.deviceAddress = device.deviceAddress;
                config.wps.setup = WpsInfo.PBC;
                mManager.connect(mChannel, config, null);
            }
        }
    }


    private void setupIntentFilter() {
        intentFilter = new IntentFilter();
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_DISCOVERY_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
    }

    private WifiP2pManager.PeerListListener createPeerListener() {
        return new WifiP2pManager.PeerListListener() {
            @Override
            public void onPeersAvailable(WifiP2pDeviceList peers) {
                peerList.clear();
                peerList.addAll(peers.getDeviceList());
                if(peerList.size() == 0){
                    Toast.makeText(context, "No devices found", Toast.LENGTH_SHORT).show();
                }
                if(role == Role.CLIENT)
                    connectToGroupOwner();
                else if(role == Role.SERVER) {
                     Toast.makeText(context, "Trying to create group", Toast.LENGTH_SHORT).show();
                    mManager.createGroup(mChannel, null);
                }

            }
        };
    }
}
