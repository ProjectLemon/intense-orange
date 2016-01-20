package org.projectlemon.intenseorange.ui;


import android.net.wifi.p2p.WifiP2pDevice;
import android.widget.ArrayAdapter;

import org.projectlemon.intenseorange.controller.interfaces.CallbackObject;

import java.io.InputStream;
import java.util.Map;


public class DetectForNearbyGames implements CallbackObject {

    private ArrayAdapter<String> adapter;

    public DetectForNearbyGames(ArrayAdapter<String> adapter) {
        this.adapter = adapter;
    }

    public void handleData(InputStream data) {}
    public void onError(int reason) {}
    public void onException(Exception e) {}

    public void notifyServerChange(Map<String, WifiP2pDevice> availableServers) {
        for (Object o : availableServers.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            adapter.add((String) pair.getKey());
        }
    }
}