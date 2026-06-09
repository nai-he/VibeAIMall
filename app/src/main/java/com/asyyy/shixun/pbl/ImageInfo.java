package com.asyyy.shixun.pbl;
public class ImageInfo {
    private int imageResId;
    private String description;

    public ImageInfo(int imageResId, String description) {
        this.imageResId = imageResId;
        this.description = description;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDescription() {
        return description;
    }
}