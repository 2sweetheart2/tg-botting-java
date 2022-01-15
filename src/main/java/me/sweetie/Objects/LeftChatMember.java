package me.sweetie.Objects;

import com.google.gson.annotations.SerializedName;

public class LeftChatMember {
    @SerializedName("id")
    private int id;
    @SerializedName("is_bot")
    private boolean bot;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("username")
    private String userName;


    public int getId() {
        return id;
    }

    public boolean isBot() {
        return bot;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }
}
