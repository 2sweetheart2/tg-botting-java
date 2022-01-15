package me.sweetie.Objects.Buttons;

import me.sweetie.Objects.Enums.Colors;
import org.json.JSONObject;

public class Button {
    private Object action;
    private Colors color;

    public Button setAction(TextButton action) {
        this.action = action;
        return this;
    }

    public Button setAction(OpenLinkButton action) {
        this.action = action;
        return this;
    }

    public Button setAction(LocationButton action) {
        this.action = action;
        return this;
    }

    public Button setAction(CallBackButton action) {
        this.action = action;
        return this;
    }

    public Button setColor(Colors color) {
        this.color = color;
        return this;
    }

    public JSONObject getButtonAsJson() {
        JSONObject act = new JSONObject(action);

        JSONObject main = new JSONObject();
        if (color != null) main.put("color", color.color);
        main.put("action", act);
        return main;
    }
}
