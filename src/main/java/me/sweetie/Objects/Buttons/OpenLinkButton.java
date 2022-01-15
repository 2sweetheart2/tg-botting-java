package me.sweetie.Objects.Buttons;

public class OpenLinkButton {
    private final String type = "open_link";
    private String link;
    private String label;
    private String payload;

    public OpenLinkButton setLabel(String label) {
        this.label = label;
        return this;
    }

    public OpenLinkButton setLink(String link) {
        this.link = link;
        return this;
    }

    public OpenLinkButton setPayload(String payload) {
        this.payload = payload;
        return this;
    }

    public String getLink() {
        return link;
    }

    public String getLabel() {
        return label;
    }

    public String getPayload() {
        return payload;
    }

    public String getType() {
        return type;
    }
}
