package org.projectlemon.intenseorange.ui.screen.server;

import android.content.Intent;
import android.os.Bundle;

import org.projectlemon.intenseorange.R;
import org.projectlemon.intenseorange.controller.interfaces.CallbackObject;
import org.projectlemon.intenseorange.model.server.Server;
import org.projectlemon.intenseorange.ui.ServerDataRetriever;
import org.projectlemon.intenseorange.ui.screen.Screen;

public class Lobby extends Screen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        Intent intent = getIntent();
        String serverName = intent.getStringExtra("serverName");

        CallbackObject callback = new ServerDataRetriever();
        Server server = new Server(this, callback, serverName);
        new Thread(server).start();
    }
}

