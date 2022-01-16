package me.sweetie.main;

import me.sweetie.Interfaces.Callback;
import me.sweetie.Interfaces.ProcessOutput;
import me.sweetie.Objects.SendMessage;
import me.sweetie.Objects.SendPhoto;
import me.sweetie.Objects.Sticker;
import me.sweetie.requsts.HttpsRequsts;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class Bot {
    private static String TOKEN;
    private static long ID;
    private static List<String> PREFIX;
    public static Logger log = Logger.getLogger(Bot.class.getName());
    private static boolean flag = false;

    public Bot(@NotNull String token, List<String> PREFIXS) {
        TOKEN = token;
        setPREFIX(PREFIXS);
    }

    public void setBreakAfterCommand(boolean flag) {
        this.flag = flag;
    }

    private boolean setPREFIX(List<String> prefix) {
        if (PREFIX != null) return false;
        PREFIX = prefix;
        return true;
    }

    public static List<String> getPREFIX() {
        return PREFIX;
    }

    public static boolean getBreakAfterCommand() {
        return flag;
    }

    public static String getToken() {
        return TOKEN;
    }


    public void sendMessage(SendMessage message) {
        HttpsRequsts.method("sendMessage", message, (response, code) -> {
        });
    }

    public void sendPhoto(SendPhoto message){
        HttpsRequsts.method("sendPhoto",message,(response, code) -> {});
    }
    public void sendSticker(Sticker sticker){
        HttpsRequsts.method("sendSticker",sticker,(response, code) -> {});
    }
    public void kick(int chatId, int userId,ProcessOutput callback){
        HttpsRequsts.method("kickChatMember","chat_id="+chatId+"&user_id="+userId,callback);
    }
    public void unban(int chatId, int userId,ProcessOutput callback){
        HttpsRequsts.method("unbanChatMember","chat_id="+chatId+"&user_id="+userId,callback);
    }

    public void sendMethd(String method, String args, ProcessOutput callback) {
        try {
            HttpsRequsts.sendPost(method, args, callback);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onReady(){}


}
