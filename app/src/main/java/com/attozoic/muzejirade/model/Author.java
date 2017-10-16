package com.attozoic.muzejirade.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.Map;



@Parcel
public class Author extends BaseEntity {

    private int id;
    private String name;
    private String description;
    private String url;

    @SerializedName("avatar_urls")
    private Map<Integer, String> avatarUrls;

    public Author() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<Integer, String> getAvatarUrls() {
        return avatarUrls;
    }

    public void setAvatarUrls(Map<Integer, String> avatarUrls) {
        this.avatarUrls = avatarUrls;
    }
}
