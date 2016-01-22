package org.projectlemon.intenseorange.ui.screen.server;

import android.content.Intent;
import android.os.Bundle;

import org.projectlemon.intenseorange.R;
import org.projectlemon.intenseorange.controller.interfaces.CallbackObject;
import org.projectlemon.intenseorange.model.server.Server;
import org.projectlemon.intenseorange.ui.ServerDataRetriever;
import org.projectlemon.intenseorange.ui.screen.Screen;

public class Lobby extends Screen {

    private Server server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        Intent intent = getIntent();
        String serverName = intent.getStringExtra("serverName");

        CallbackObject callback = new ServerDataRetriever();
        server = new Server(this, callback, serverName);
        new Thread(server).start();
    }

    /* register the broadcast receiver with the intent values to be matched */
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(server.receiver, server.intentFilter);
    }
    /* unregister the broadcast receiver */
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(server.receiver);
    }
}

