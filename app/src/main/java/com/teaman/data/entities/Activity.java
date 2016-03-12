package com.teaman.data.entities;

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
public class Activity extends ParseObject {

    public static String TYPE_FAVORITE = "favorite";
    public static String TYPE_REVIEW = "review";
    public static String TYPE_FRIEND = "friend";

    private String fromUsername;
    private String toUsername;
    private String type;
    private String content;
    private String establishmentName;
}
