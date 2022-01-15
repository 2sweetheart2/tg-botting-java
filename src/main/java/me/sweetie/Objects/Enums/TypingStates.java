package me.sweetie.Objects.Enums;

public enum TypingStates {
    TYPING("typing"),
    UNKNOWN("unknown");

    public final String state;

    TypingStates(String typing) {
        this.state = typing;
    }
}
