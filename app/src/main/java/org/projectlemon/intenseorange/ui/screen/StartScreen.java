package org.projectlemon.intenseorange.ui.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.projectlemon.intenseorange.R;
import org.projectlemon.intenseorange.controller.implementations.NetworkController;
import org.projectlemon.intenseorange.model.utilities.Role;
import org.projectlemon.intenseorange.ui.screen.server.GameSetup;

public class StartScreen extends Screen {

    private NetworkController networkController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        networkController = new NetworkController(this, Role.SERVER, null);
        networkController.start();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.view_list_item);
        adapter.add("Nearby game 1");
        adapter.add("Nearby game 2");
        ((ListView) findViewById(R.id.nearby_games_list)).setAdapter(adapter);
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, GameSetup.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        networkController.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        networkController.onPause();
    }
}
