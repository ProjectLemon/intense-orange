package org.projectlemon.intenseorange.ui;

import android.net.wifi.p2p.WifiP2pDevice;
import android.widget.ArrayAdapter;

import org.projectlemon.intenseorange.controller.interfaces.CallbackObject;

import java.io.InputStream;
import java.util.Map;

/**
 * File: ServerDataRetriever.java
 * Project: Intense Orange
 * Author: Fredrik Johansson
 * Date: 2016.01.20
 */

public class ServerDataRetriever implements CallbackObject {

    public ServerDataRetriever() {
    }

    public void handleData(InputStream data) {
    }

    public void onError(int reason) {
    }

    public void notifyServerChange(Map<String, WifiP2pDevice> availableServers) {

    }
}
