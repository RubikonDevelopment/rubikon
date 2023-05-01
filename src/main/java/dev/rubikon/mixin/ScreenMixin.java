package dev.rubikon.mixin;

import dev.rubikon.things.Things;
import dev.rubikon.things.commands.Commands;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Screen.class)
public class ScreenMixin {
    @Inject(
            method = "handleTextClick",
            at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;)V", ordinal = 1, remap = false),
            cancellable = true
    )
    public void onHandleTextClick(Style style, CallbackInfoReturnable<Boolean> cir) {
        if (style.getClickEvent() == null) return;

        String value = style.getClickEvent().getValue();

        if (!value.startsWith(".")) return;

        try {
            Commands.get().dispatch(value.substring(1));

            cir.setReturnValue(true);
            cir.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
