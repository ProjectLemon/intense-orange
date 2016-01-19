package org.projectlemon.intenseorange.ui.screen.client;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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
        RelativeLayout r = (RelativeLayout) (view.getParent()).getParent();
        r.setBackground(view.getBackground());
        System.out.println(r);
        System.out.println(view);
    }
}
