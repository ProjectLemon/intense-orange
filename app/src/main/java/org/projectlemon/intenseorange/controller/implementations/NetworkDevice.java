package org.projectlemon.intenseorange.controller.implementations;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Looper;
import android.widget.Toast;

import org.projectlemon.intenseorange.controller.interfaces.CallbackObject;

import java.io.IOException;

/**
 * TODO: document this class
 * Created by linuslagerhjelm on 16-01-19.
 */
public abstract class NetworkDevice {

    public IntentFilter intentFilter;
    protected WifiP2pManager mManager;
    protected WifiP2pManager.Channel mChannel;
    protected CallbackObject callback;
    protected Context context;

    public NetworkDevice(Context context, CallbackObject callable) {
        this.context = context;
        this.callback = callable;

        mManager = (WifiP2pManager)context.getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(context, Looper.getMainLooper(), null);
        setupIntentFilter();
    }

    public abstract void sendData(byte[] data) throws IOException;
    public abstract void receiveData(byte[] data) throws NullPointerException;
    public abstract void setNickname(String nickname) throws IllegalArgumentException;



    private void setupIntentFilter() {
        intentFilter = new IntentFilter();
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_DISCOVERY_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
    }
}
