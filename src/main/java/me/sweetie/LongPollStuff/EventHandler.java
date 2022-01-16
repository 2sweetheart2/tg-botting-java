package me.sweetie.LongPollStuff;

import com.google.gson.Gson;
import me.sweetie.Interfaces.Callback;
import me.sweetie.Objects.*;
import me.sweetie.main.Bot;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;

public class EventHandler {
    private final Gson gson = new Gson();
    private final HashMap<String, Object> commands = new HashMap<>();

    protected void onMessageNew(Message message) {
    }

    protected void onMessageEdit(Message message) {
    }

    protected void onSomeEvent(String type, JSONObject object) {
    }


    protected void onGroupChatTitleUpdate(String newChatName, Message message) {
    }

    protected void onGroupChatKickUser(int memberId, Message message) {
    }

    protected void onGroupChatPhotoUpdate(Message message, NewChatPhoto newChatPhoto) {
    }

    protected void onGroupChatPhotoRemove(Message message) {
    }


    protected void onGroupChatLeaveMember(Message message, LeftChatMember leftChatMember) {
    }

    protected void onGroupChatInviteMember(Message message, NewChatMember newChatMember){
    }
    protected void onReady() {
    }

    private Message currentMessage;

    protected String parse(String type, Object o) {
        switch (type) {
            case "message": {
                JSONObject message = ((JSONObject) o);
                Message m = gson.fromJson(message.toString(), Message.class);
                if (message.has("chat"))
                    m.setChat(gson.fromJson(message.getJSONObject("chat").toString(), Chat.class));
                if (message.has("from"))
                    m.setFromUser(gson.fromJson(message.getJSONObject("from").toString(), User.class));
                if(message.has("new_chat_member")){
                    NewChatMember newChatMember = gson.fromJson(message.getJSONObject("new_chat_member").toString(),NewChatMember.class);
                    onGroupChatInviteMember(m,newChatMember);
                    break;
                }
                if(message.has("left_chat_member")){
                    LeftChatMember leftChatMember = gson.fromJson(message.getJSONObject("left_chat_member").toString(),LeftChatMember.class);
                    onGroupChatLeaveMember(m,leftChatMember);
                    break;
                }
                if(message.has("new_chat_title")){
                    onGroupChatTitleUpdate(message.getString("new_chat_title"),m);
                    break;
                }
                if(message.has("new_chat_photo")){
                    NewChatPhoto newChatPhoto = gson.fromJson(message.getJSONArray("new_chat_photo").get(0).toString(),NewChatPhoto.class);
                    onGroupChatPhotoUpdate(m,newChatPhoto);
                    break;
                }
                currentMessage = m;
                if (m.getText()!=null) {
                    for(String i : Bot.getPREFIX()){
                        if(m.getText().startsWith(i)){
                            Callback r = Search(m.getText().replace(i, ""));
                            if (r != null) r.cumback(m,args);
                            break;
                        }
                    }
                    if (Bot.getBreakAfterCommand()) break;
                }

                onMessageNew(m);
                break;
            }
            case "new_chat_participant": {
                Message m = gson.fromJson(o.toString(), Message.class);
                LeftChatMember left = gson.fromJson(((JSONObject) o).getJSONObject("left_chat_member").toString(), LeftChatMember.class);
                onGroupChatLeaveMember(m, left);
                break;
            }
            case "message_edit": {
                Message m = gson.fromJson(o.toString(), Message.class);
                onMessageEdit(m);
                break;
            }
            default: {
                System.out.println("NEW EVENT="+type+"\n"+o);
                onSomeEvent(type, (JSONObject) o);
                break;
            }
        }
        return "ok";
    }

    private String currentCommand;
    private String[] args;

    private Callback _Search(HashMap<String, Object> map, String[] msg) {
        if (msg.length > 0 && map.containsKey(msg[0])) {
            Callback res = _Search((HashMap<String, Object>) map.get(msg[0]), Arrays.copyOfRange(msg, 1, msg.length));
            if (res != null) {
                return res;
            }
        }
        if (map.containsKey("")) {
            args = msg;
            return (Callback) map.get("");
        }
        return null;
    }

    private Callback Search(String name) {
        currentCommand = name;
        return _Search(commands, Arrays.copyOfRange(name.split("\\s+"), 1, name.split("\\s+").length));
    }

    private void _AddCommand(HashMap<String, Object> map, String[] name, Callback command) {
        if (name.length == 0) {
            map.put("", command);
            return;
        }
        if (!map.containsKey(name[0])) {
            map.put(name[0], new HashMap<String, Object>());
        }
        _AddCommand((HashMap<String, Object>) map.get(name[0]), Arrays.copyOfRange(name, 1, name.length), command);
    }

    private void AddCommand(String name, Callback command) {
        _AddCommand(commands, name.split("\\s+"), command);
    }


    protected void regCommand(String command, Callback callback) {

        this.AddCommand(command, callback);
    }

}