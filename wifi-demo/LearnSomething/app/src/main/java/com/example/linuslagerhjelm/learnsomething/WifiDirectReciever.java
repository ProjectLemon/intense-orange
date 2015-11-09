package com.example.linuslagerhjelm.learnsomething;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.widget.Toast;


/**
 * Created by linuslagerhjelm on 15-11-08.
 */
public class WifiDirectReciever extends BroadcastReceiver{

    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    private MainActivity mActivity;
    WifiP2pDeviceList peerList = new WifiP2pDeviceList();

    WifiP2pManager.PeerListListener peerListListener = new WifiP2pManager.PeerListListener() {
        @Override
        public void onPeersAvailable(WifiP2pDeviceList peers) {
            peerList = peers;
            mActivity.textView.setText(peers.toString());
        }
    };


    public WifiDirectReciever(WifiP2pManager mManager, WifiP2pManager.Channel mChannel, MainActivity mainActivity) {
        super();
        this.mManager = mManager;
        this.mChannel = mChannel;
        this.mActivity = mainActivity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                Toast.makeText(context, "Wifi p2p enabled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Wifi p2p not enabled", Toast.LENGTH_SHORT).show();
            }
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {

            if (mManager != null) {
                    mManager.requestPeers(mChannel, peerListListener);
                }

        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {

        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {

        }
    }
}
