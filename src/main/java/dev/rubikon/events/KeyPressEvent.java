package dev.rubikon.events;

import dev.rubikon.api.commons.Event;

public class KeyPressEvent implements Event {
    private final int action;
    private final int key;
    public KeyPressEvent(int key, int action) {
        this.key = key;
        this.action = action;
    }

    public int getKey() {
        return key;
    }

    public int getAction() {
        return action;
    }
}
