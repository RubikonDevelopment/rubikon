package dev.inditium.mixin;

import dev.inditium.api.renderer.core.nanovg.NVContext;
import net.minecraft.client.render.GameRenderer;
import org.lwjgl.nanovg.NVGColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.lwjgl.nanovg.NanoVG.*;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(at = @At("TAIL"), method = "render")
    private void init(CallbackInfo info) {
        //only for initializing nanoVG
        NVContext.draw((vg) ->{
            nvgRect(vg, 10, 10, 100, 100);
            nvgFillColor(vg, nvgColor(-1));
            nvgFill(vg);
        });
    }
    private static NVGColor nvgColor(int argb) {
        NVGColor _res = NVGColor.create();
        nvgRGBA((byte) (argb >> 16 & 255), (byte) (argb >> 8 & 255), (byte) (argb >> 0 & 255), (byte) (argb >> 24 & 255), _res);
        return _res;
    }
}
