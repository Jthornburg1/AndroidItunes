package com.example.jon_thornburg.secondituneshitter.activities.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jon_thornburg on 1/5/17.
 */

public class ItunesResponse {

    @SerializedName("resultCount")
    private int resultCount;
    @SerializedName("results")
    private List<SongItem> results;

    // Getters
    public int getResultCount() { return resultCount;}
    public List<SongItem> getResults() { return results; }

    // Setters
    public void setResultCount(int resultCount) { this.resultCount = resultCount; }
    public void setResults(List<SongItem> results) { this.results = results; }
}
