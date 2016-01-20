package org.projectlemon.intenseorange.ui.screen.client;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import org.projectlemon.intenseorange.R;
import org.projectlemon.intenseorange.ui.screen.Screen;

/**
 * File: TeamSetup.java
 * Project: Intense Orange
 * Author: Fredrik Johansson
 * Date: 2015.11.25
 */

public class TeamSetup extends Screen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_setup);
    }

    public void chooseColor(View view) {
        RelativeLayout layout = (RelativeLayout) (view.getParent()).getParent();
        layout.setBackground(view.getBackground());

        // Set text color (black/white) with best contrast to the chosen color
        // see: http://stackoverflow.com/questions/3942878/how-to-decide-font-color-in-white-or-black-depending-on-background-color
        ColorDrawable color = (ColorDrawable) view.getBackground();
        double r = Color.red(color.getColor());
        double g = Color.green(color.getColor());
        double b = Color.blue(color.getColor());

        r /= 255;
        r = (r <= 0.03928) ? r/12.92 : Math.pow((r+0.055)/1.055, 2.4);
        g /= 255;
        g = (g <= 0.03928) ? g/12.92 : Math.pow((g+0.055)/1.055, 2.4);
        b /= 255;
        b = (b <= 0.03928) ? b/12.92 : Math.pow((b+0.055)/1.055, 2.4);
        double l = 0.2126 * r + 0.7152 * g + 0.0722 * b;

        EditText enterTeamName = (EditText) findViewById(R.id.enter_team_name);
        if (l > 0.179) {
            enterTeamName.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        } else {
            enterTeamName.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
        }


    }
}
