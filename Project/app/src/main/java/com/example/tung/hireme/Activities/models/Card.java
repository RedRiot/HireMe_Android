package com.example.tung.hireme.Activities.models;

import android.app.Fragment;

/**
 * Created by tung on 4/16/18.
 */

public class Card {
    private String userId;
    private String name;
    private String summary;
    private String profileImageUrl;

    public Card() {
    }

    public Card(String userId, String name, String summary, String profileImageUrl) {
        this.userId = userId;
        this.name = name;
        this.summary = summary;
        this.profileImageUrl = profileImageUrl;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
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
