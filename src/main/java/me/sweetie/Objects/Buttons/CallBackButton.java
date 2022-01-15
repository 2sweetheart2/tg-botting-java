package me.sweetie.Objects.Buttons;

public class CallBackButton {
    private final String type = "callback";
    private String label;
    private String payload;

    public String getType() {
        return type;
    }

    public String getPayload() {
        return payload;
    }

    public String getLabel() {
        return label;
    }

    public CallBackButton setLabel(String label) {
        this.label = label;
        return this;
    }

    public CallBackButton setPayload(String payload) {
        this.payload = payload;
        return this;
    }

}
