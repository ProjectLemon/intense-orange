package org.projectlemon.intenseorange.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Looper;

import org.projectlemon.intenseorange.model.network.WifiDirectReciever;
import org.projectlemon.intenseorange.model.utilities.Role;

import java.util.concurrent.Callable;

/**
 * Created by Jenny on 2015-11-11.
 */
public class NetworkController {
    WifiP2pManager mManager;
    WifiP2pManager.Channel mChannel;
    BroadcastReceiver wifiReciever;
    IntentFilter intentFilter;
    WifiP2pManager.PeerListListener peerListener;
    Context context;
    Role role;
    Callable callbackFunction;

    public NetworkController (Context context, Role role, Callable function ) {
        this.context = context;
        this.role = role;
        this.callbackFunction = function;

        mManager = (WifiP2pManager)context.getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(context, Looper.getMainLooper(), null);
        wifiReciever = new WifiDirectReciever();
    }

}
