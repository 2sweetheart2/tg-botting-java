package me.sweetie.requsts;

import me.sweetie.Interfaces.ProcessOutput;
import me.sweetie.LongPollStuff.LongPoll;
import me.sweetie.Objects.SendMessage;
import me.sweetie.Utils.ObjectToParams;
import me.sweetie.main.Bot;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;


public class HttpsRequsts {


    public static void sendPost(String method, String params, ProcessOutput callback) throws IOException {
        String url = "https://api.telegram.org/bot" + Bot.getToken() + '/' + method + '?' + params;
        if (method.equals("getLongPollServices/" + LongPoll.getAddres())) {
            url = LongPoll.getAddres() + "?" + params;
        }
        HttpsURLConnection httpClient = (HttpsURLConnection) new URL(url).openConnection();
        httpClient.setRequestMethod("POST");
//        httpClient.setRequestProperty("User-Agent", "KateMobileAndroid/52.1 lite-445 (Android 4.4.2; SDK 19; x86; unknown Android SDK built for x86; en)");
//        httpClient.setRequestProperty("Accept","image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, mp3, */*");

        int responseCode = httpClient.getResponseCode();
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            JSONObject res = new JSONObject(response.toString());
            if (res.has("error")) {
                Bot.log.log(Level.WARNING, res.getJSONObject("error").getString("error_msg"));
                return;
            }
            callback.procesJson(res, responseCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void sendGet(String method, ProcessOutput callback, Object... params) throws IOException {
        String url = "https://api.telegram.org/bot" + Bot.getToken() + '/' + method;
        HttpURLConnection httpClient =
                (HttpURLConnection) new URL(url).openConnection();
        httpClient.setRequestMethod("GET");
        //  httpClient.setRequestProperty("User-Agent", "KateMobileAndroid/52.1 lite-445 (Android 4.4.2; SDK 23; x86; unknown Android SDK built for x86; en)");
//        httpClient.setRequestProperty("User-Agent", "VKAndroidApp/4.13.1-1206 (Android 4.4.3; SDK 19; armeabi; ; ru)");
//        httpClient.setRequestProperty("Accept","image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, audio/mp3 */*");
        int responseCode = httpClient.getResponseCode();
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }


            callback.procesJson(new JSONObject(response.toString()), responseCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void method(String method, Object obj, ProcessOutput callback) {
        try {
            switch (method) {
                case "sendMessage": {
                    SendMessage msg = (SendMessage) obj;
                    String params = ObjectToParams.castMessageToParams(msg);
                    sendPost(method, params, callback);
                    break;
                }
                case "groups.getLongPollServer": {
                    sendPost(method, (String) obj, callback);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
