package me.sweetie.Objects;

import me.sweetie.Objects.Buttons.Button;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class KeyBoard {
    private boolean oneTime = false;
    private List<JSONObject> textButtons = new ArrayList<>();
    private List<JSONObject> openLinkButtons = new ArrayList<>();
    private List<JSONObject> locationButtons = new ArrayList<>();
    private List<JSONObject> callbackButtons = new ArrayList<>();

    public KeyBoard setOneTime(boolean oneTime) {
        this.oneTime = oneTime;
        return this;
    }

    public KeyBoard setTextButtons(List<Button> textButtons) {
        for (Button b : textButtons) {
            this.textButtons.add(b.getButtonAsJson());
        }
        return this;
    }

    public KeyBoard setOpenLinkButtons(List<Button> openLinkButtons) {
        for (Button b : openLinkButtons) {
            this.openLinkButtons.add(b.getButtonAsJson());
        }
        return this;
    }

    public KeyBoard setLocationButtons(List<Button> locationButtons) {
        for (Button b : locationButtons) {
            this.locationButtons.add(b.getButtonAsJson());
        }
        return this;
    }

    public KeyBoard setCallBackButton(List<Button> callBackButtons) {
        for (Button b : callBackButtons) {
            this.callbackButtons.add(b.getButtonAsJson());
        }
        return this;
    }

    public boolean getOneTime() {
        return oneTime;
    }

    public JSONArray getButtonsAsJson() {
        JSONArray allbtn = new JSONArray();
        JSONArray textbtn = new JSONArray();
        JSONArray oplinkbtn = new JSONArray();
        JSONArray locbtn = new JSONArray();
        JSONArray callbackbtn = new JSONArray();
        for (JSONObject o : textButtons) {
            textbtn.put(o);
        }
        for (JSONObject o : openLinkButtons) {
            oplinkbtn.put(o);
        }
        for (JSONObject o : locationButtons) {
            locbtn.put(o);
        }
        for (JSONObject o : callbackButtons) {
            callbackbtn.put(o);
        }
        if (textbtn.length() != 0) allbtn.put(textbtn);
        if (oplinkbtn.length() != 0) allbtn.put(oplinkbtn);
        if (locbtn.length() != 0) allbtn.put(locbtn);
        if (callbackbtn.length() != 0) allbtn.put(callbackbtn);
        return allbtn;
    }
}
