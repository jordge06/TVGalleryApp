package com.example.tvapp.responses;

import com.example.tvapp.models.TVModel;
import com.example.tvapp.models.TVShow;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVShowModelResponse {

    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private List<TVModel> tvShows;

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<TVModel> getTvShows() {
        return tvShows;
    }
}
