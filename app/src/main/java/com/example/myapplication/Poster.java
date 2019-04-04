package com.example.myapplication;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

public class Poster {
    @SerializedName("width")
    private int width;
    @SerializedName("height")
    private int height;
    @SerializedName("file_path")
    private String filePath;
    private Bitmap image;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
