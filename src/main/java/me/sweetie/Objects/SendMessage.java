package me.sweetie.Objects;

import org.json.JSONObject;

public class SendMessage {
    private String chatId;
    private String text;
    private String parseMod;
    private boolean disableWebPagePreview = false;
    private boolean disableNotification = false;
    private int replyToMessageId = -1;
    private JSONObject replyMarkup;


    public SendMessage(String text, String chatId) {
        this.chatId = chatId;
        this.text = text;
    }


    public String getChatId() {
        return chatId;
    }

    public String getText() {
        return text;
    }

    public SendMessage setParseMod(String parseMod) {
        this.parseMod = parseMod;
        return this;
    }

    public String getParseMod() {
        return parseMod;
    }

    public boolean isDisableWebPagePreview() {
        return disableWebPagePreview;
    }

    public SendMessage setDisableWebPagePreview(boolean disableWebPagePreview) {
        this.disableWebPagePreview = disableWebPagePreview;
        return this;
    }

    public SendMessage setDisableNotification(boolean disableNotification) {
        this.disableNotification = disableNotification;
        return this;
    }

    public boolean isDisableNotification() {
        return disableNotification;
    }

    public SendMessage setReplyToMessageId(int replyToMessageId) {
        this.replyToMessageId = replyToMessageId;
        return this;
    }

    public int getReplyToMessageId() {
        return replyToMessageId;
    }

    public void setReplyMarkup(JSONObject replyMarkup) {
        this.replyMarkup = replyMarkup;
    }

    public JSONObject getReplyMarkup() {
        return replyMarkup;
    }
}
