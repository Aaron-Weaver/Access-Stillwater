package com.teaman.data.entities.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by weava on 3/13/16.
 */
public class Results<T>
{
    @SerializedName("results")
    @Expose
    private List<T> results;

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
