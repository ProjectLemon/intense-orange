package org.projectlemon.intenseorange.model.utilities.helpers;

/**
 * This class implements common helper methods that is not tied to a specific part of the API
 *
 * Created by linuslagerhjelm on 16-01-19.
 */

//TODO: Might need to refactor the name to something better
public class CommonHelpers {

    /**
     * Check wether the given string is null or empty
     * @param str the string to check
     * @return true if string was null or empty
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
