package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Images {
    @SerializedName("posters")
    private List<Poster> posters;

    public List<Poster> getPosters() {
        return posters;
    }

    public void setPosters(List<Poster> posters) {
        this.posters = posters;
    }
}
