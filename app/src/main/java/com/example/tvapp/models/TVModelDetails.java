package com.example.tvapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVModelDetails {

    private String name;

    private String overview;

    private String status;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("origin_country")
    private String[] originCountry;

    private String homepage;

    private GenreModel[] genres;

    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("popularity")
    private float rating;

    @SerializedName("poster_path")
    private String posterPath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public GenreModel[] getGenres() {
        return genres;
    }

    public void setGenres(GenreModel[] genres) {
        this.genres = genres;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String[] getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String[] originCountry) {
        this.originCountry = originCountry;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
