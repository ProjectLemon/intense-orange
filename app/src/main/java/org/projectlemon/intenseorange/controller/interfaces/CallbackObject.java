package org.projectlemon.intenseorange.controller.interfaces;

/**
 * Created by linuslagerhjelm on 15-11-25.
 */
public interface CallbackObject extends Runnable{
    void handleData(byte[] data);
}
