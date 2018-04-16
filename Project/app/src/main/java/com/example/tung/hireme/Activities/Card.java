package com.example.tung.hireme.Activities;

/**
 * Created by tung on 4/16/18.
 */

public class Card {
    private String userId;
    private String name;
    private String summary;

    public Card(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.summary = "Hi, I am a junior software developer. Please Hire Me!";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
