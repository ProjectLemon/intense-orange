package org.projectlemon.intenseorange.controller.interfaces;

/**
 * Class: NetworkControllerInterface
 * Purpose: Public interface for the network controller
 *          This interface includes methods that manages the wifi functionality for
 *
 * Created by linuslagerhjelm on 15-11-12.
 */
public interface NerworkControllerInterface {

    /**
     * Registers this object as a broadcast receiver for wifi broadcasts within the system
     */
    void onResume();

    /**
     * Unregister this object as a broadcast receiver for wifi broadcasts within the system
     */
    void onPause();

    /**
     * Upon calling this method. The device will open a wifi direct connection with surrounding
     * devices that is part of the game. Depending on it's role, the device will act as a server
     * or as a client that connects to a group.
     */
    void start();


    void pause();

    /**
     * Called to close the connection. This method should only be called when everything is
     * finished and the connection not is to be opened since it would cause a renegotiation
     * of roles and connection establishment. Which is time and battery consuming.
     *
     * Use pause instead to save battery
     */
    void close();

    /**
     * This method will send the specified data to the connected devices
     * @param data the data to send
     */
    void sendData(byte[] data);

    /**
     * This method will call the handleData()-method with the received data
     * @param data the data to receive
     */
    void receiveData(byte[] data);
}
