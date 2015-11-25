package org.projectlemon.intenseorange.controller.interfaces;

/**
 * Class: NetworkControllerInterface
 * Purpose: Public interface for the network controller
 *          This interface includes methods that manages
 *
 * Created by linuslagerhjelm on 15-11-12.
 */
public interface NerworkControllerInterface {
    void onResume();
    void onPause();
    void start();
    void pause();
    void close();
    void sendData(byte[] data);
    void receiveData(byte[] data);
}
