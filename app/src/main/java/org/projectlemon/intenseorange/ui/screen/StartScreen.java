package org.projectlemon.intenseorange.ui.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.projectlemon.intenseorange.R;
import org.projectlemon.intenseorange.ui.screen.server.GameSetup;

public class StartScreen extends Screen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, GameSetup.class);
        startActivity(intent);
    }
}
