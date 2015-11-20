package org.projectlemon.intenseorange.controller.implementations;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Looper;
import android.widget.Toast;

import org.projectlemon.intenseorange.controller.interfaces.NerworkControllerInterface;
import org.projectlemon.intenseorange.model.network.WifiDirectReciever;
import org.projectlemon.intenseorange.model.utilities.Role;


/**
 * Created by Jenny on 2015-11-11.
 */
public class NetworkController implements NerworkControllerInterface {
    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    private BroadcastReceiver wifiReciever;
    private IntentFilter intentFilter;
    private Context context;
    private Role role;
    private Runnable callbackFunction;
    private WifiP2pDeviceList peerList;
    private WifiP2pManager.PeerListListener peerListener;

    public NetworkController (Context context, Role role, Runnable function ) {
        this.context = context;
        this.role = role;
        this.callbackFunction = function;

        peerListener = createPeerListener();
        mManager = (WifiP2pManager)context.getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(context, Looper.getMainLooper(), null);
        wifiReciever = new WifiDirectReciever(mManager, mChannel, context, peerListener);
        setupIntentFilter();

    }

    @Override
    public void onResume() {
        context.registerReceiver(wifiReciever, intentFilter);
    }

    @Override
    public void onPause() {
        context.unregisterReceiver(wifiReciever);
    }

    @Override
    public void start() {
        mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(context, "Searching... ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int reason) {
                Toast.makeText(context, "Discover peers failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void pause() {

    }

    @Override
    public void close() {

    }

    @Override
    public void sendData() {

    }

    private void connectToGroupOwner() {

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
                peerList = peers;
                connectToGroupOwner();
            }
        };
    }
}
