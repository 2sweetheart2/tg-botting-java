package me.sweetie.LongPollStuff;

import com.google.gson.Gson;
import me.sweetie.Objects.Chat;
import me.sweetie.Objects.LeftChatMember;
import me.sweetie.Objects.Message;
import me.sweetie.Objects.User;
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

    protected void onGroupChatInviteUser(int memberId, Message message) {
    }

    protected void onGroupChatKickUser(int memberId, Message message) {
    }

    protected void onGroupChatPhotoUpdate(Message message) {
    }

    protected void onGroupChatPhotoRemove(Message message) {
    }

    protected void onCommand(Message message, String command, String[] args) {
    }

    protected void onGroupChatLeaveUser(Message message, LeftChatMember leftChatMember) {

    }

    private Message currentMessage;

    protected String parse(String type, Object o) {
        switch (type) {
            case "message": {
                JSONObject message = ((JSONObject) o);
                Message m = gson.fromJson(message.toString(), Message.class);
                if (message.has("chat")) m.setChat(gson.fromJson(message.getJSONObject("chat").toString(), Chat.class));
                if (message.has("from"))
                    m.setFromUser(gson.fromJson(message.getJSONObject("from").toString(), User.class));
                currentMessage = m;
                if (m.getText() != null) {
                    if (m.getText().startsWith(Bot.getPREFIX())) {
                        String r = Search(m.getText().replace(Bot.getPREFIX(), ""));
                        if (r != null) onCommand(currentMessage, r, currentCommand.replace(r, "").split("\\s+"));
                        if (Bot.getBreakAfterCommand()) break;
                    }
                }
                onMessageNew(m);
                break;
            }
            case "new_chat_participant": {
                Message m = gson.fromJson(o.toString(), Message.class);
                LeftChatMember left = gson.fromJson(((JSONObject) o).getJSONObject("left_chat_member").toString(), LeftChatMember.class);
                onGroupChatLeaveUser(m, left);
                break;
            }
            case "message_edit": {
                Message m = gson.fromJson(o.toString(), Message.class);
                onMessageEdit(m);
                break;
            }
            default: {
                onSomeEvent(type, (JSONObject) o);
                break;
            }
        }
        return "ok";
    }

    private String currentCommand;

    private String _Search(HashMap<String, Object> map, String[] msg) {
        if (msg.length > 0 && map.containsKey(msg[0])) {
            String res = _Search((HashMap<String, Object>) map.get(msg[0]), Arrays.copyOfRange(msg, 1, msg.length));
            if (res != null) {
                return res;
            }
        }
        if (map.containsKey("")) {
            return (String) map.get("");
        }
        return null;
    }

    private String Search(String name) {
        currentCommand = name;
        return _Search(commands, Arrays.copyOfRange(name.split("\\s+"), 1, name.split("\\s+").length));
    }

    private void _AddCommand(HashMap<String, Object> map, String[] name, String command) {
        if (name.length == 0) {
            map.put("", command);
            return;
        }
        if (!map.containsKey(name[0])) {
            map.put(name[0], new HashMap<String, Object>());
        }
        _AddCommand((HashMap<String, Object>) map.get(name[0]), Arrays.copyOfRange(name, 1, name.length), command);
    }

    private void AddCommand(String name, String command) {
        _AddCommand(commands, name.split("\\s+"), command);
    }


    protected void regCommand(String command) {

        this.AddCommand(command, command);
    }

}