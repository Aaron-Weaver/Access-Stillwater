package com.teaman.accessstillwater.utils;

/**
 * <h1> [Insert class name here] </h1>
 * <p>
 * [Insert class description here]
 * </p>
 * <p>
 * [Insert additional information here (links, code snippets, etc.)]
 * </p>
 *
 * @author Aaron Weaver
 *         Team Andronerds
 *         waaronl@okstate.edu
 * @version 1.0
 * @since 2/23/16
 */
public class StringUtils {

    public static boolean isNullOrEmpty(String checkString) {
        if(checkString == null || checkString.trim().isEmpty()) {
            return true;
        }
        return false;
    }
}
