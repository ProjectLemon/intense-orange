package org.projectlemon.intenseorange.model.utilities;

/**
 * Created by linuslagerhjelm on 16-01-18.
 */
public class NetworkVariables {
    public static short PORT = 29561;
    public static int ID = 0;

    public static int getID() {
        return ++ID;
    }
}
