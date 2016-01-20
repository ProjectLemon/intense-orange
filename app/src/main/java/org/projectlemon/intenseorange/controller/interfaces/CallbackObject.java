package org.projectlemon.intenseorange.controller.interfaces;

import android.net.wifi.p2p.WifiP2pDevice;

import java.io.InputStream;
import java.util.Map;

/**
 * This class declares an interface for a callback object that is used by the view to receive data
 * from the system.
 *
 * Created by linuslagerhjelm on 15-11-25.
 */
public interface CallbackObject {

    /**
     * This function is used to PDUs as inputstream from the system
     *
     * @param data the PDU as inputstream
     */
    void handleData(InputStream data);

    /**
     * This method is used for reporting async errors to the GUI
     *
     * @param reason Reason code
     */
    void onError(int reason);

    /**
     * This method is called whenever the system detects a change in available servers
     *
     * @param availableServers a list of servers.
     */
    void notifyServerChange(Map<String,WifiP2pDevice> availableServers);
}
