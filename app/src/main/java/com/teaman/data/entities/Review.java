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
@ParseClassName("Review")
public class Review extends BaseParseObject<Review> {

    private ParseUser byUser;
    private String title;
    private String content;
    private int totalRating;
    private int auditoryRating;
    private int visualRating;
    private int physicalRating;

    public int getTotalRating() {
        return (getAuditoryRating() + getPhysicalRating() + getVisualRating()) / 3;
    }

    public void setTotalRating() {
        this.totalRating = getTotalRating();
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public int getAuditoryRating()
    {
        return auditoryRating;
    }

    public void setAuditoryRating(int auditoryRating)
    {
        this.auditoryRating = auditoryRating;
    }

    public int getVisualRating()
    {
        return visualRating;
    }

    public void setVisualRating(int visualRating)
    {
        this.visualRating = visualRating;
    }

    public int getPhysicalRating()
    {
        return physicalRating;
    }

    public void setPhysicalRating(int physicalRating)
    {
        this.physicalRating = physicalRating;
    }

    public ParseUser getByUser() {
        return byUser;
    }

    public void setByUser(ParseUser byUser) {
        this.byUser = byUser;
    }

    @Override
    public Review instance()
    {
        return this;
    }

    public static ParseQuery<Review> getQuery() {
        return ParseQuery.getQuery(Review.class);
    }
}
