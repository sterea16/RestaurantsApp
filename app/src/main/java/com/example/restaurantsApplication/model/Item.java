package com.example.restaurantsApplication.model;

public class Item {
    private String title;
    private String subtitle;
    private String icon;
    private String[] photos;

    public Item(String title, String subtitle, String icon, String[] photos) {
        this.icon = icon;
        this.title = title;
        this.subtitle = subtitle;
        this.photos = photos;
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String[] getPhotos() {
        return photos;
    }
}
