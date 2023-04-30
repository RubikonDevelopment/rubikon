package dev.rubikon.mixin;

import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Inject(method = "onKey",at = @At(value = "FIELD",target = "Lnet/minecraft/client/Keyboard;switchF3State:Z"))
    private void key(long window, int key, int scancode, int action, int modifiers,CallbackInfo info) {
    }
}
