package com.example.tvapp.models;

import com.google.gson.annotations.SerializedName;

public class BackgroundImages {

    @SerializedName("file_path")
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
