package com.example.tvapp.responses;

import com.example.tvapp.models.GenreModel;
import com.google.gson.annotations.SerializedName;

public class GenreResponse {

    @SerializedName("genres")
    private GenreModel[] genres;

    public GenreModel[] getGenres() {
        return genres;
    }

    public void setGenres(GenreModel[] genres) {
        this.genres = genres;
    }
}
