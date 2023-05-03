package dev.rubikon.mixin;

import com.llamalad7.mixinextras.injector.ModifyReceiver;
import dev.rubikon.Rubikon;
import dev.rubikon.events.ScreenRenderEvent;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;


@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Shadow public abstract void tick();

    @Inject(at = @At("TAIL"), method = "render")
    private void init(float tickDelta, long startTime, boolean tick,CallbackInfo info) {
        //findme :: screen render call
        Rubikon.getEventPubSub().publish(new ScreenRenderEvent(tickDelta));
    }

//    @ModifyReceiver(,at = @At("INVOKE"), method = "loadPrograms")
//    private void loadshaders(TargetClass info) {
//        //findme :: screen render call
//    }

    @ModifyReceiver(
            method = "loadPrograms",
            at = @At(value = "INVOKE", target = "Ljava/util/Arraylist;add(Lcom/mojang/datafixers/util/Pair)V")
    )
    private ArrayList changeObject(ArrayList receiver) {
        return receiver;
    }
}
