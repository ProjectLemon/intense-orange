package org.projectlemon.intenseorange.ui.screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.projectlemon.intenseorange.R;

/**
 * Super class for all screens to simplify setup and
 * to avoid any difference among screens.
 * Where a screen is a any "page" that a user sees.
 */
public abstract class Screen extends AppCompatActivity {

    /**
     * Do basic setup, for example enable fullscreen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        enableFullscreen();
    }

    /**
     * Make sure screen is always in fullscreen
     *
     * @param hasFocus If screen is in focus or not
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //setTheme(R.style.Screen);

        if (hasFocus) {
            enableFullscreen();
        }
    }

    /**
     * Setup all flags to enable full immersive fullscreen
     */
    private void enableFullscreen() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    }
}
