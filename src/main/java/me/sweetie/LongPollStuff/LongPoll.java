package me.sweetie.LongPollStuff;

import me.sweetie.main.Bot;
import me.sweetie.requsts.HttpsRequsts;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class LongPoll extends EventHandler {
    private String key = null;
    private static String addres = null;
    private long ts;


    public LongPoll(String token) {
        addres = "https://api.telegram.org/bot" + token + "/getUpdates";
        ts = 0;
        run();

    }

    protected void run() {
        Executors.newSingleThreadExecutor().execute(
                () -> {
                    try {
                        handleUpdates();
                    } catch (Exception e) {
                        e.printStackTrace();
                        run();
                    }
                }
        );
    }

    private volatile boolean start = false;

    private void handleUpdates() {
        if (addres == null) {
            Bot.log.log(Level.WARNING, "Getting LongPoll server was failed");
            return;
        }
        start = true;
        Bot.log.info("LongPoll handler started to handle events");
        try {
            onReady();
            while (true) {
                HttpsRequsts.sendPost("getLongPollServices/" + addres, "offset=" + ts, (response, code) -> {
                    if (response.getBoolean("ok")) {
                        JSONArray a = response.getJSONArray("result");
                        for (int i = 0; i < a.length(); i++) {
                            JSONObject a_ = (JSONObject) a.get(i);
                            ts = a_.getLong("update_id") + 1;
                            String event = a_.toString().split("\"")[3];
                            parse(event, a_.getJSONObject(event));
                        }
                    }
                });
            }
        } catch (IOException e) {
            Bot.log.info("LongPoll handler stopped to handle events");
            e.printStackTrace();
        }
    }

    public static String getAddres() {
        return addres;
    }



}