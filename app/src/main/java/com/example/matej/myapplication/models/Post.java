package com.example.matej.myapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post {

    @SerializedName("blog_name")
    @Expose
    private String blogName;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("post_url")
    @Expose
    private String postUrl;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("timestamp")
    @Expose
    private int timestamp;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("reblog_key")
    @Expose
    private String reblogKey;
    @SerializedName("tags")
    @Expose
    private List<Object> tags = null;
    @SerializedName("note_count")
    @Expose
    private int noteCount;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("photos")
    @Expose
    private List<Photo> photos = null;

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getReblogKey() {
        return reblogKey;
    }

    public void setReblogKey(String reblogKey) {
        this.reblogKey = reblogKey;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public int getNoteCount() {
        return noteCount;
    }

    public void setNoteCount(int noteCount) {
        this.noteCount = noteCount;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}