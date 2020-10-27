package com.example.restaurantsApplication.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Item implements Serializable {

    /*This class represents a single element inside a JsonArray which is within another JsonArray*/
    public static class NestedPhoto implements Serializable{

        @SerializedName("imagePath")
        private String imagePath;

        public String getImagePath() {
            return imagePath;
        }
    }

    @SerializedName("name")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("imagePath")
    private String icon;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("photos")
    private NestedPhoto[] nestedPhotoArray;


    public Item(String icon){
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NestedPhoto[] getNestedPhotoArray() {
        return nestedPhotoArray;
    }

    public void setNestedPhotoArray(NestedPhoto[] nestedPhotoArray) {
        this.nestedPhotoArray = nestedPhotoArray;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
