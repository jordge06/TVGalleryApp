package com.example.tvapp.responses;

import com.example.tvapp.models.BackgroundImages;
import com.google.gson.annotations.SerializedName;

public class TVShowImagesResponse {

    @SerializedName("backdrops")
    private BackgroundImages[] backgroundImages;

    public BackgroundImages[] getBackgroundImages() {
        return backgroundImages;
    }

    public void setBackgroundImages(BackgroundImages[] backgroundImages) {
        this.backgroundImages = backgroundImages;
    }
}
