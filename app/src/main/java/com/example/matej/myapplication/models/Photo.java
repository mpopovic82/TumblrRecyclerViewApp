package com.example.matej.myapplication.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("alt_sizes")
    @Expose
    private List<AltSize> altSizes = null;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<AltSize> getAltSizes() {
        return altSizes;
    }

    public void setAltSizes(List<AltSize> altSizes) {
        this.altSizes = altSizes;
    }

}