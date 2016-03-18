package com.teaman.data.entities;

import com.parse.ParseClassName;
import com.parse.ParseQuery;
import com.teaman.accessstillwater.base.BaseParseObject;

import java.util.List;

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
@ParseClassName("Establishment")
public class Establishment extends BaseParseObject<Establishment> {

    public static String TYPE_RESTAURAUNT = "restauraunt";
    public static String TYPE_BAR = "bar";
    public static String TYPE_COFFEE_TEA = "coffeTea";
    public static String TYPE_SHOPPING = "shopping";
    public static String TYPE_DRUGSTORES = "drugstores";
    public static String TYPE_PUBLIC_GOVERNMENT = "publicGovernment";
    public static String TYPE_ARTS_ENTERTAINMENT = "artsEntertainment";

    private String placesId;

    public String getPlacesId() {
        return placesId;
    }

    public void setPlacesId(String placesId) {
        this.placesId = placesId;
    }

    public int getTotalRatingWithReviews(List<Review> reviews) {
        int reviewCount = reviews.size();
        int totalRating = 0;

        for (Review rev : reviews) {
            totalRating += rev.getTotalRating();
        }

        if(reviewCount > 0) {
            return totalRating / reviewCount;
        }
        return 0;
    }


    @Override
    public Establishment instance()
    {
        return this;
    }

    public static ParseQuery<Establishment> getQuery() {
        return ParseQuery.getQuery(Establishment.class);
    }
}
