package com.teaman.data.entities;

import com.parse.ParseClassName;
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

    private String title;
    private String content;
    private float auditoryRating;
    private float visualRating;
    private float physicalRating;

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

    public float getAuditoryRating()
    {
        return auditoryRating;
    }

    public void setAuditoryRating(float auditoryRating)
    {
        this.auditoryRating = auditoryRating;
    }

    public float getVisualRating()
    {
        return visualRating;
    }

    public void setVisualRating(float visualRating)
    {
        this.visualRating = visualRating;
    }

    public float getPhysicalRating()
    {
        return physicalRating;
    }

    public void setPhysicalRating(float physicalRating)
    {
        this.physicalRating = physicalRating;
    }

    @Override
    public Review instance()
    {
        return this;
    }
}
