package com.example.restaurantsApplication.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ItemPhoto implements Serializable {


    @SerializedName("imagePath")
    private String imagePath;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
