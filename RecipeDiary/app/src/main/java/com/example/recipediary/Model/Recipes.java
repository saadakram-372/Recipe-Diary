package com.example.recipediary.Model;

public class Recipes {

    private String title;
    private String url;

    public Recipes(String name, String urls) {
        this.title = name;
        this.url = urls;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}

