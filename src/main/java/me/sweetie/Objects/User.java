package me.sweetie.Objects;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("language_code")
    private String languageCode;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("id")
    private int id;
    @SerializedName("is_bot")
    private boolean bot;
    @SerializedName("first_name")
    private String firstName;

    public String getLanguageCode(){
        return languageCode;
    }

    public int getId() {
        return id;
    }

    public boolean isBot() {
        return bot;
    }
    public String getLastName(){
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getName(){
        return firstName+" "+lastName;
    }
}
