package com.teaman.data.entities;

import com.parse.ParseClassName;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.teaman.accessstillwater.base.BaseParseObject;

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
@ParseClassName("Activity")
public class Activity extends BaseParseObject<Activity> {

    public static String TYPE_FAVORITE = "favorite";
    public static String TYPE_REVIEW = "review";
    public static String TYPE_FRIEND = "friend";

    private ParseUser fromUser;
    private ParseUser toUser;
    private String type;
    private String content;
    private Establishment establishment;
    private Review review;

    public Activity() {

    }

    @Override
    public Activity instance()
    {
        return this;
    }

    public ParseUser getFromUser() {
        return fromUser;
    }

    public void setFromUser(ParseUser fromUsername) {
        this.fromUser = fromUsername;
    }

    public ParseUser getToUser() {
        return toUser;
    }

    public void setToUser(ParseUser toUsername) {
        this.toUser = toUsername;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishmentName) {
        this.establishment = establishmentName;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public static ParseQuery<Activity> getQuery() {
        return ParseQuery.getQuery(Activity.class);
    }
}
