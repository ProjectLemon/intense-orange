package org.projectlemon.intenseorange.ui.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.projectlemon.intenseorange.R;
import org.projectlemon.intenseorange.controller.interfaces.CallbackObject;
import org.projectlemon.intenseorange.model.client.Client;
import org.projectlemon.intenseorange.ui.DetectForNearbyGames;
import org.projectlemon.intenseorange.ui.screen.client.TeamSetup;
import org.projectlemon.intenseorange.ui.screen.server.GameSetup;

import java.io.IOException;


/**
 * First screen the user sees. Displays the ability to start a new game and to join others
 */
public class StartScreen extends Screen {


    /**
     * Setup screen with Client to look for nearby games and
     * display the to the user.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.view_list_item);
        ((ListView) findViewById(R.id.nearby_games_list)).setAdapter(adapter);

        CallbackObject detecter = new DetectForNearbyGames(adapter);
        Client client;
        try {
            client = new Client(this, detecter);
            new Thread(client).start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Switch screen to GameSetup
     *
     * @param view The view which is pressed
     */
    public void startGame(View view) {
        Intent intent = new Intent(this, GameSetup.class);
        startActivity(intent);
    }

    /**
     * Switch screen to TeamSetup
     *
     * @param view The view which is pressed
     */
    public void setupTeam(View view) {
        Intent intent = new Intent(this, TeamSetup.class);
        startActivity(intent);
    }

    /* register the broadcast receiver with the intent values to be matched */
    @Override
    protected void onResume() {
        super.onResume();
        //registerReceiver(mReceiver, (network device in));
    }
    /* unregister the broadcast receiver */
    @Override
    protected void onPause() {
        super.onPause();
        //unregisterReceiver(mReceiver);
    }
}
