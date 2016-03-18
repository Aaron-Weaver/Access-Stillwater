package com.teaman.accessstillwater.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static final String ESTABLISHMENT_TYPE_EXTRA = "establishmentFragType";
    public static final String REVIEW_TYPE_EXTRA = "reviewFragType";

    private static String API_KEY = "AIzaSyA9Zm_3nqNWTIyX8vbC0FpSmFLCH6z8z9A";

    private static final String EMAIL_REGEX = "^[\\\\w\\\\.-]+@([\\\\w\\\\-]+\\\\.)+[A-Z]{2,4}$";

    public static String MAPS_API_PHOTO_URL= "https://maps.googleapis.com/maps/api/place/photo?maxwidth=1600&key=" + API_KEY + "&photoreference=";

    public static boolean isNullOrEmpty(String checkString) {
        if(checkString == null || checkString.trim().isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isValidEmail(String checkString) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(checkString);

        if(matcher.matches()) {
            return true;
        }
        return false;
    }
}
