package me.sweetie.Utils;

import me.sweetie.Objects.SendMessage;
import me.sweetie.main.Bot;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ObjectToParams {
    private static final String token = Bot.getToken();
    private static final Float v = 5.999f;

    public static String castMessageToParams(SendMessage message) {
        StringBuilder text = new StringBuilder();
        text.append(toUTF8("text")).append('=').append(toUTF8(message.getText())).append("&");
        if (message.getChatId() < 0)
            text.append(toUTF8("chat_id")).append('=').append(toUTF8("-100" + String.valueOf(message.getChatId()).replace("-", ""))).append("&");
        else text.append(toUTF8("chat_id")).append('=').append(toUTF8(String.valueOf(message.getChatId()))).append("&");
        if (message.getParseMod() != null)
            text.append(toUTF8("parse_mode")).append('=').append(toUTF8(message.getParseMod())).append('&');
        text.append(toUTF8("disable_web_page_preview")).append('=').append(toUTF8(String.valueOf(message.isDisableWebPagePreview()))).append("&");
        text.append(toUTF8("disable_notification")).append('=').append(toUTF8(String.valueOf(message.isDisableNotification()))).append("&");
        if (message.getReplyToMessageId() >= 0)
            text.append(toUTF8("reply_to_message_id")).append('=').append(toUTF8(String.valueOf(message.getReplyToMessageId()))).append('&');
        if (message.getReplyMarkup() != null)
            text.append(toUTF8("reply_markup")).append('=').append(toUTF8(message.getReplyMarkup().toString())).append('&');
        if (text.toString().endsWith("&")) return text.substring(0, text.length() - 1);
        return text.toString();
    }

    private static String toUTF8(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String addStaticalParams(String params) {
        return "access_token=" + token + '&' + "v=" + v + '&' + params;
    }
}
