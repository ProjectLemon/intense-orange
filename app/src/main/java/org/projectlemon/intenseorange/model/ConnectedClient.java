package org.projectlemon.intenseorange.model;

import android.net.wifi.p2p.WifiP2pDevice;

import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Jenny on 2015-11-11.
 * Class for clients. It will write and send messages to clients.
 */
public class ConnectedClient {
    private Socket socket;
    private OutputStream outputstream;
    private Enum role;

    ConnectedClient(WifiP2pDevice peer){

    }

    public void writeMessage(){

    }

    private void recieveMessage(){

    }

}
