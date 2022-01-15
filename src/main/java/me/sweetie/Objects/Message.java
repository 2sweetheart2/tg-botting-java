package me.sweetie.Objects;

import com.google.gson.annotations.SerializedName;
import org.json.JSONObject;

public class Message {

    @SerializedName("chat")
    private Chat chat;
    @SerializedName("message_id")
    private int messageId;
    @SerializedName("date")
    private long date;
    @SerializedName("text")
    private String text;
    @SerializedName("from")
    private User fromUser;

    private KeyBoard keyBoard = null;
    private JSONObject botForwardMessage;


    public void setKeyBoard(KeyBoard keyBoard) {
        this.keyBoard = keyBoard;
    }

    public String getKeyBoard() {
        if (keyBoard == null) return null;
        JSONObject keyboard = new JSONObject();
        keyboard.put("one_time", this.keyBoard.getOneTime());
        keyboard.put("buttons", this.keyBoard.getButtonsAsJson());
        return keyboard.toString();
    }

    public Message setText(String message) {
        this.text = message;
        return this;
    }


    public String getText() {
        return text;
    }


    public long getDate() {
        return date;
    }

    public int getMessageId() {
        return messageId;
    }

    public Chat getChat() {
        return chat;
    }

    public User getFromUser() {
        return fromUser;
    }

    public JSONObject getBotForwardMessage() {
        return botForwardMessage;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }
    //    public void setReply(Message message) {
//        setPeerId(message.getPeerId());
//        JSONObject j =new JSONObject(String.format("{\"peer_id\":%s,\"is_reply\":\"%s\",\"conversation_message_ids\":%s}", message.getPeerId(), getText(), message.getConvMessageId()));
//        botForwardMessage = j;
//    }
}
