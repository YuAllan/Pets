package com.example.allan.pets.models;

public class UserAccountSettings {

    private String description;
    private String display_name;
    private String profile_photo;
    private String username;

    public UserAccountSettings(String description, String display_name, String profile_photo, String username) {
        this.description = description;
        this.display_name = display_name;
        this.profile_photo = profile_photo;
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "UserAccountSettings{" + "description='" + description + '\'' + ", display_name='" + display_name + '\'' + ", profile_photo='" + profile_photo + '\'' + ", username='" + username + '\'' + '}';
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public UserAccountSettings() {

    }
}
