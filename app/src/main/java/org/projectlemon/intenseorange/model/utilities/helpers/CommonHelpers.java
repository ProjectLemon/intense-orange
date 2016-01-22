package org.projectlemon.intenseorange.model.utilities.helpers;

/**
 * This class implements common helper methods that is not tied to a specific part of the API
 *
 * Created by linuslagerhjelm on 16-01-19.
 */

public class CommonHelpers {

    /**
     * Check wether the given string is null or empty
     * @param str the string to check
     * @return true if string was null or empty
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * This method is used to remove the full domain name from the server broadcast in order to
     * display it properly.
     *
     * @param str the string to extract name from
     * @return the name without domain
     */
    public static String extractServerName(String str) {
        String returnstring = new String();
        for(int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == '.') {
                break;
            }
            returnstring += str.charAt(i);
        }
        return returnstring;
    }
}
