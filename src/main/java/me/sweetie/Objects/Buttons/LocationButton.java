package me.sweetie.Objects.Buttons;

public class LocationButton {
    private final String type = "location";
    private String payload;

    public String getPayload() {
        return payload;
    }

    public String getType() {
        return type;
    }

    public LocationButton setPayload(String payload) {
        this.payload = payload;
        return this;
    }
}
