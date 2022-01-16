package me.sweetie.Objects;

import org.json.JSONObject;

public class SendPhoto {
    private int chatId;
    private String photoId;
    private String caption;
    private boolean disableNotification = false;
    private int replyToMessageId = -1;
    private JSONObject replyMarkup;
    public SendPhoto(int chatId, String photoId){
        this.chatId = chatId;
        this.photoId = photoId;
    }

    public int getChatId() {
        return chatId;
    }

    public String getPhotoId() {
        return photoId;
    }

    public SendPhoto setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public SendPhoto setDisableNotification(boolean disableNotification) {
        this.disableNotification = disableNotification;
        return this;
    }

    public boolean isDisableNotification() {
        return disableNotification;
    }

    public SendPhoto setReplyToMessageId(int replyToMessageId) {
        this.replyToMessageId = replyToMessageId;
        return this;
    }

    public int getReplyToMessageId() {
        return replyToMessageId;
    }

    public SendPhoto setReplyMarkup(JSONObject replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    public JSONObject getReplyMarkup() {
        return replyMarkup;
    }
}
