package com.example.jon_thornburg.secondituneshitter.activities.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jon_thornburg on 1/5/17.
 */

public class SongItem implements Serializable {

    @SerializedName("trackId")
    private String trackId;
    @SerializedName("artistName")
    private String artistName;
    @SerializedName("collectionName")
    private String collectionName;
    @SerializedName("trackName")
    private String trackName;
    @SerializedName("artistViewUrl")
    private String artistViewUrl;
    @SerializedName("artworkUrl60")
    private String artworkUrl60;
    @SerializedName("releaseDate")
    private String releaseDate;
    @SerializedName("country")
    private String country;
    @SerializedName("primaryGenreName")
    private String primaryGenreName;
    @SerializedName("artworkUrl100")
    private String artworkUrl100;

    public SongItem(String trackId, String artistName, String collectionName, String trackName,
                    String artistViewUrl, String artworkUrl60, String releaseDate, String country,
                    String primaryGenreName, String artworkUrl100) {
        this.trackId = trackId;
        this.artistName = artistName;
        this.collectionName = collectionName;
        this.trackName = trackName;
        this.artistViewUrl = artistViewUrl;
        this.artworkUrl60 = artworkUrl60;
        this.releaseDate = releaseDate;
        this.country = country;
        this.primaryGenreName = primaryGenreName;
        this.artworkUrl100 = artworkUrl100;
    }

    // Getters
    public String getTrackId() { return trackId; }
    public String getArtistName() { return artistName; }
    public String getCollectionName() { return collectionName; }
    public String getTrackName() { return trackName; }
    public String getArtistViewUrl() { return artistViewUrl; }
    public String getArtworkUrl60() { return artworkUrl60; }
    public String getReleaseDate() { return releaseDate; }
    public String getCountry() { return country; }
    public String getPrimaryGenreName() { return primaryGenreName; }
    public String getArtworkUrl100() { return artworkUrl100; }

    // Setters
    public void setTrackId(String trackId) { this.trackId = trackId; }
    public void setArtistName(String artistName) { this.artistName = artistName; }
    public void setCollectionName(String collectionName) { this.collectionName = collectionName; }
    public void setTrackName(String trackName) { this.trackName = trackName; }
    public void setArtistViewUrl(String artistViewUrl) { this.artistViewUrl = artistViewUrl; }
    public void setArtworkUrl60(String artworkUrl60) { this.artworkUrl60 = artworkUrl60; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }
    public void setCountry(String country) { this.country = country; }
    public void setPrimaryGenreName(String primaryGenreName) { this.primaryGenreName = primaryGenreName; }
    public void setArtworkUrl100(String artworkUrl100) { this.artworkUrl100 = artworkUrl100; }
}
