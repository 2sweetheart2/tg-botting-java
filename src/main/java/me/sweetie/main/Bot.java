package me.sweetie.main;

import me.sweetie.Interfaces.ProcessOutput;
import me.sweetie.Objects.SendMessage;
import me.sweetie.requsts.HttpsRequsts;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.logging.Logger;

public class Bot {
    private static String TOKEN;
    private static long ID;
    private static String PREFIX;
    public static Logger log = Logger.getLogger(Bot.class.getName());
    private static boolean flag = false;

    public Bot(@NotNull String token, String PREFIX) {
        TOKEN = token;
        setPREFIX(PREFIX);
    }

    public void setBreakAfterCommand(boolean flag) {
        this.flag = flag;
    }

    private boolean setPREFIX(String prefix) {
        if (PREFIX != null) return false;
        PREFIX = prefix;
        return true;
    }

    public static String getPREFIX() {
        return PREFIX;
    }

    public static boolean getBreakAfterCommand() {
        return flag;
    }

    public static String getToken() {
        return TOKEN;
    }


    public boolean sendMessage(SendMessage message) {
        HttpsRequsts.method("sendMessage", message, (response, code) -> {
        });
        return true;
    }

    public void sendMethd(String method, String args, ProcessOutput callback) {
        try {
            HttpsRequsts.sendPost(method, args, callback);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
