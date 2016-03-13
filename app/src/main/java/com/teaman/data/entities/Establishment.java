package com.teaman.data.entities;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
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
@ParseClassName("Establishment")
public class Establishment extends BaseParseObject<Establishment> {

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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public ParseGeoPoint getLocation()
    {
        return location;
    }

    public void setLocation(ParseGeoPoint location)
    {
        this.location = location;
    }

    public String getPrimaryImageURL()
    {
        return primaryImageURL;
    }

    public void setPrimaryImageURL(String primaryImageURL)
    {
        this.primaryImageURL = primaryImageURL;
    }

    public String[] getAlternateImageURLs()
    {
        return alternateImageURLs;
    }

    public void setAlternateImageURLs(String[] alternateImageURLs)
    {
        this.alternateImageURLs = alternateImageURLs;
    }

    public float getAuditoryRating()
    {
        return auditoryRating;
    }

    public void setAuditoryRating(float auditoryRating)
    {
        this.auditoryRating = auditoryRating;
    }

    public float getPhysicalRating()
    {
        return physicalRating;
    }

    public void setPhysicalRating(float physicalRating)
    {
        this.physicalRating = physicalRating;
    }

    public float getVisualRating()
    {
        return visualRating;
    }

    public void setVisualRating(float visualRating)
    {
        this.visualRating = visualRating;
    }

    @Override
    public Establishment instance()
    {
        return this;
    }
}
