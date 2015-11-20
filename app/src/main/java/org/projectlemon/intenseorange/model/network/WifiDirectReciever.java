package org.projectlemon.intenseorange.model.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.nio.channels.Channel;

/**
 * Created by linuslagerhjelm on 15-11-11.
 */
public class WifiDirectReciever extends BroadcastReceiver {

    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    private Context context;
    private WifiP2pManager.PeerListListener peerListener;

    public WifiDirectReciever(WifiP2pManager m, WifiP2pManager.Channel c, Context ctx,
                              WifiP2pManager.PeerListListener listener) {
        this.mManager = m;
        this.mChannel = c;
        this.context = ctx;
        peerListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            handleStateChanged(intent);

        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            //TODO: Find out what this does and maybe implement some code

        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            //TODO: Find out what this does and maybe implement some code

        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            handlePeersChangedAction();
        }
    }



    private void handleStateChanged( Intent intent ) {
        int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
        if ( !(state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) ) {
            try {
                Method method1 = mManager.getClass().getMethod("enableP2p", Channel.class);
                method1.invoke(mManager, mChannel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void handlePeersChangedAction () {
        try {
            mManager.requestPeers(mChannel, peerListener);
        } catch (NullPointerException e) {
            Toast.makeText(context, "Trying to resolve network error", Toast.LENGTH_SHORT).show();
        }
    }
}
