package com.example.restaurantsApplication.model;


import com.google.gson.annotations.SerializedName;

public class Item {

    /*This class represents a single element inside a JsonArray which is within another JsonArray*/
    private static class NestedPhoto {

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

    @SerializedName("photos")
    private NestedPhoto[] nestedPhotoArray;

    private String[] photoArray;

    public void createPhotoArray(){
        photoArray = new String[nestedPhotoArray.length];
        for(int i = 0; i < nestedPhotoArray.length; ++i)
            photoArray[i] = nestedPhotoArray[i].getImagePath();
    }

    public void setPhotoArray(String[] photoArray) {
        this.photoArray = photoArray;
    }

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

    public String[] getPhotoArray() {
        return photoArray;
    }

    public NestedPhoto[] getNestedPhotoArray() {
        return nestedPhotoArray;
    }

    public void setNestedPhotoArray(NestedPhoto[] nestedPhotoArray) {
        this.nestedPhotoArray = nestedPhotoArray;
    }

}
