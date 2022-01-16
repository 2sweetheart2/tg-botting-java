package me.sweetie.Objects;

import com.google.gson.annotations.SerializedName;

public class Chat {
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("type")
    private String type;


    public String getId() {
        return id;
    }

    public Chat setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Chat setType(String type) {
        this.type = type;
        return this;
    }

    public String getType() {
        return type;
    }
}
