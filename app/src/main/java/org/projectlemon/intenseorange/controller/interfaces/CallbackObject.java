package org.projectlemon.intenseorange.controller.interfaces;

import java.io.InputStream;

/**
 * This class declares an interface for a callback object that is used by the view to receive data
 * from the system.
 *
 * Created by linuslagerhjelm on 15-11-25.
 */
public interface CallbackObject extends Runnable{

    /**
     * This function is used to PDUs as inputstream from the system
     * @param data the PDU as inputstream
     */
    void handleData(InputStream data);
}
