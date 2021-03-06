package org.projectlemon.intenseorange.ui.screen.server;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.projectlemon.intenseorange.R;
import org.projectlemon.intenseorange.controller.interfaces.CallbackObject;
import org.projectlemon.intenseorange.ui.ServerDataRetriever;
import org.projectlemon.intenseorange.ui.screen.Screen;


public class GameSetup extends Screen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setup);

    }

    public void setGameName(View view) {
        EditText text = (EditText) findViewById(R.id.enter_game_name_text);


        Intent intent = new Intent(this, Lobby.class);
        intent.putExtra("serverName", text.getText().toString());
        startActivity(intent);
    }

}

