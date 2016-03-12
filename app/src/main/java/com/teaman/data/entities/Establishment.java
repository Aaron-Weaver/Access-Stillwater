package com.teaman.data.entities;

import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

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
 * @since 3/10/16
 */
public class Establishment extends ParseObject {

    public static String TYPE_RESTAURAUNT = "restauraunt";
    public static String TYPE_BAR = "bar";
    public static String TYPE_COFFEE_TEA = "coffeTea";
    public static String TYPE_SHOPPING = "shopping";
    public static String TYPE_DRUGSTORES = "drugstores";
    public static String TYPE_PUBLIC_GOVERNMENT = "publicGovernment";
    public static String TYPE_ARTS_ENTERTAINMENT = "artsEntertainment";

    private String name;
    private String type;
    private ParseGeoPoint location;
    private String primaryImageURL;
    private String[] alternateImageURLs;
    private float auditoryRating;
    private float physicalRating;
    private float visualRating;
}
