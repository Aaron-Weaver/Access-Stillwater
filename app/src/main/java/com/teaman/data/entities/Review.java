package com.teaman.data.entities;

import com.parse.ParseClassName;
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
@ParseClassName("Review")
public class Review extends ParseObject {

    private String title;
    private String content;
    private float auditoryRating;
    private float visualRating;
    private float physicalRating;
}
