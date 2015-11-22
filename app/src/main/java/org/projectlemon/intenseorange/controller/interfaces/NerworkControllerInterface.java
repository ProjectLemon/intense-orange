package org.projectlemon.intenseorange.controller.interfaces;

/**
 * Created by linuslagerhjelm on 15-11-12.
 */
public interface NerworkControllerInterface {
    void onResume();
    void onPause();
    void start();
    void pause();
    void close();
    void sendData(byte[] data);
}
