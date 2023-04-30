package dev.rubikon.mixin;

import dev.rubikon.Rubikon;
import dev.rubikon.events.KeyPressEvent;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Inject(method = "onKey",at = @At(value = "HEAD"))
    private void key(long window, int key, int scancode, int action, int modifiers,CallbackInfo info) {
        //findme :: key event call
        Rubikon.getEventPubSub().publish(new KeyPressEvent(key,action));
    }
}
