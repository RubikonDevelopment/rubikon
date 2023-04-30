package dev.rubikon.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface OnKeyPressCallback {
    Event<OnKeyPressCallback> EVENT = EventFactory.createArrayBacked(OnKeyPressCallback.class, (listeners) -> (key) -> {
        for (OnKeyPressCallback event : listeners) {
            event.press(key);
        }
    });
    /**
     * checks if a key is pressed
     * @param key the key that is being pressed
     */
    void press(int key);
}
