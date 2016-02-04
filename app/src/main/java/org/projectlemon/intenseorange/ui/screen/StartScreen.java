package org.projectlemon.intenseorange.ui.screen;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pDevice;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.projectlemon.intenseorange.R;
import org.projectlemon.intenseorange.controller.interfaces.CallbackObject;
import org.projectlemon.intenseorange.model.client.Client;
import org.projectlemon.intenseorange.model.utilities.helpers.DebugHelper;
import org.projectlemon.intenseorange.ui.DetectForNearbyGames;
import org.projectlemon.intenseorange.ui.screen.client.TeamSetup;
import org.projectlemon.intenseorange.ui.screen.server.GameSetup;

import java.util.Map;
import java.util.HashSet;
import java.util.Set;


/**
 * First screen the user sees. Displays the ability to start a new game and to join others
 */
public class StartScreen extends Screen {

    private Client client;
    private Map<String, WifiP2pDevice> availableServers;
    private CallbackObject detecter;
    private DebugHelper debug = new DebugHelper("StartScreen");
    private Set<String> nearbyServers;
    private SharedPreferences storedData;
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

        detecter = new DetectForNearbyGames(adapter);
        client = new Client(this, detecter);
        new Thread(client).start();
        registerReceiver(client.receiver, client.intentFilter);

        storedData = this.getSharedPreferences("serverInfo", Activity.MODE_PRIVATE);
        nearbyServers = storedData.getStringSet("nearbyServers", new HashSet<String>());
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
        availableServers = client.getAvailableServers();
        detecter.notifyServerChange(availableServers);
        registerReceiver(client.receiver, client.intentFilter);
    }


    /* unregister the broadcast receiver */
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(client.receiver);
    }

    @Override
    protected void onStop() {
        super.onStop();
        debug.log("onStop()");

        SharedPreferences.Editor ed = storedData.edit();
        ed.putStringSet("nearbyServers", nearbyServers);
        ed.commit();
    }
}
