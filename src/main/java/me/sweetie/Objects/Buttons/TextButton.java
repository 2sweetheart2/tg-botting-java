package me.sweetie.Objects.Buttons;

public class TextButton {
    private String type = "text";
    private String payload;
    private String label;

    public TextButton setPayLoad(String payLoad) {
        this.payload = payLoad;
        return this;
    }

    public TextButton setLabel(String label) {
        this.label = label;
        return this;
    }

    public String getType() {
        return type;
    }

    public String getPayload() {
        return payload;
    }

    public String getLabel() {
        return label;
    }
}
