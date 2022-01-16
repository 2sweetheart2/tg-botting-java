package me.sweetie.Utils;

import me.sweetie.Objects.SendMessage;
import me.sweetie.Objects.SendPhoto;
import me.sweetie.Objects.Sticker;
import me.sweetie.main.Bot;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ObjectToParams {
    private static final String token = Bot.getToken();
    private static final Float v = 5.999f;

    public static String castMessageToParams(SendMessage message) {
        StringBuilder text = new StringBuilder();
        text.append(toUTF8("text")).append('=').append(toUTF8(message.getText())).append("&");
        text.append(toUTF8("chat_id")).append('=').append(message.getChatId()).append("&");
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

    public static String castPhotoToParams(SendPhoto sendPhoto){
        StringBuilder text = new StringBuilder();
        text.append("chat_id=").append(sendPhoto.getChatId()).append("&");
        text.append("photo=").append(sendPhoto.getPhotoId()).append("&");
        text.append("disable_notification=").append(sendPhoto.isDisableNotification()).append("&");
        if(sendPhoto.getCaption()!=null) text.append("caption=").append(sendPhoto.getCaption()).append("&");
        if(sendPhoto.getReplyToMessageId()!=-1) text.append("reply_to_message_id=").append(sendPhoto.getReplyToMessageId()).append("&");
        if(sendPhoto.getReplyMarkup()!=null) text.append("reply_markup=").append(sendPhoto.getReplyMarkup());
        if (text.toString().endsWith(",")) return text.substring(0, text.length() - 1);
        return text.toString();
    }

    public static String castStickerToParam(Sticker sticker){
        StringBuilder text = new StringBuilder();
        text.append("chat_id=").append(sticker.getChatId()).append("&");
        text.append("sticker=").append(sticker.getSticker()).append("&");
        text.append("disable_notification=").append(sticker.isDisableNotification()).append("&");
        if(sticker.getReplyToMessageId()!=-1) text.append("reply_to_message_id=").append(sticker.getReplyToMessageId()).append("&");
        if(sticker.getReplyMarkup()!=null) text.append("reply_markup=").append(sticker.getReplyMarkup());
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

    private static String castToChatId(int id){
        return "-100"+String.valueOf(id).replace("-","");
    }

    private static String addStaticalParams(String params) {
        return "access_token=" + token + '&' + "v=" + v + '&' + params;
    }
}
