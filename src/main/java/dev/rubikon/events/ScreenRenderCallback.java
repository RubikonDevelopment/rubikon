package dev.rubikon.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface ScreenRenderCallback {
    Event<ScreenRenderCallback> EVENT = EventFactory.createArrayBacked(ScreenRenderCallback.class, (listeners) -> (tickDelta) -> {
        for (ScreenRenderCallback event : listeners) {
            event.render(tickDelta);
        }
    });
    /**
     * Called after rendering completing the whole render loop
     * @param tickDelta Progress for linearly interpolating between the previous and current game state
     */
    void render(float tickDelta);
}
