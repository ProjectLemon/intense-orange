package org.projectlemon.intenseorange.model.utilities;

/**
 * This class contains various variables and constants that the network API rely on to function
 *
 * Created by linuslagerhjelm on 16-01-18.
 */
public class NetworkVariables {
    public static final short PORT = 29561;
    public static final int HEADER_SIZE = 16;
    public static int ID = 0;

    public static int getID() {
        return ++ID;
    }
}
