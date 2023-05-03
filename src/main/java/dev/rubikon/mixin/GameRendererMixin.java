package dev.rubikon.mixin;

import com.mojang.datafixers.util.Pair;
import dev.rubikon.Rubikon;
import dev.rubikon.events.ScreenRenderEvent;
import dev.rubikon.renderer.core.Renderer;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.resource.ResourceFactory;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;


@Mixin(GameRenderer.class)
@Debug(export = true)
public abstract class GameRendererMixin {
    @Shadow public abstract void tick();

    @Inject(at = @At("TAIL"), method = "render")
    private void init(float tickDelta, long startTime, boolean tick,CallbackInfo info) {
        //findme :: screen render call
        Rubikon.getEventPubSub().publish(new ScreenRenderEvent(tickDelta));
    }

    @Inject(
            method = "loadPrograms(Lnet/minecraft/resource/ResourceFactory;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;add(Ljava/lang/Object;)Z",
                    ordinal = 0
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void onLoadPrograms(ResourceFactory factory, CallbackInfo ci, List list, List list2) {
        try {
            list2.add(Pair.<ShaderProgram, Consumer<ShaderProgram>>of(new ShaderProgram(factory, "test", VertexFormats.POSITION), program -> {
                Renderer.getInstance().setTestProgram(program);
            }));
        } catch(IOException e) {}
    }
}
