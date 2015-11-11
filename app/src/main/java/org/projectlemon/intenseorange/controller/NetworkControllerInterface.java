package org.projectlemon.intenseorange.controller;

/**
 * Created by Jenny on 2015-11-11.
 */
interface NetworkControllerInterface {
    void onResume();
    void onPause();
    void start();
    void pause();
    void close();
    void sendData();
}
