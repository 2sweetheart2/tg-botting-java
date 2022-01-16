package me.sweetie.Objects;

import org.json.JSONObject;

public class Sticker {
    private String chatId;
    private String sticker;
    private boolean disableNotification = false;
    private int replyToMessageId = -1;
    private JSONObject replyMarkup;
    public Sticker(String chatId, String sticker){
        this.chatId = chatId;
        this.sticker = sticker;
    }

    public String getChatId() {
        return chatId;
    }

    public String getSticker() {
        return sticker;
    }


    public Sticker setDisableNotification(boolean disableNotification) {
        this.disableNotification = disableNotification;
        return this;
    }

    public boolean isDisableNotification() {
        return disableNotification;
    }

    public Sticker setReplyToMessageId(int replyToMessageId) {
        this.replyToMessageId = replyToMessageId;
        return this;
    }

    public int getReplyToMessageId() {
        return replyToMessageId;
    }

    public Sticker setReplyMarkup(JSONObject replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    public JSONObject getReplyMarkup() {
        return replyMarkup;
    }
}
