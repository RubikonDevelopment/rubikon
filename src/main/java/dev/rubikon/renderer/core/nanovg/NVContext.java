package dev.rubikon.renderer.core.nanovg;

import com.mojang.blaze3d.platform.GlConst;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.rubikon.Rubikon;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import org.lwjgl.nanovg.NVGColor;

import static org.lwjgl.nanovg.NanoVG.*;
import static org.lwjgl.nanovg.NanoVGGL3.*;

import java.util.function.Consumer;

import static org.lwjgl.system.MemoryUtil.NULL;


public class NVContext {
    private static long ctx = 0;
    private static final MinecraftClient mc = MinecraftClient.getInstance();
    public NVContext() {}

    public static void init() {
        //create nanovg context for current thread with antialiasing
        ctx = nvgCreate(NVG_ANTIALIAS);
        if (ctx == NULL) {
            //opengl 3 must be supported
            Rubikon.LOGGER.error("NVContext#initialize: Couldn't init NanoVG",new RuntimeException());
        }
        Rubikon.LOGGER.info("NanoVG was successfully initialized with opengl3 backend!");
    }

    /**
     * Draws elements on the screen.
     * @param drawCall - the lambda that will be called to draw elements on the screen.
     */
    public static void draw(Consumer<Long> drawCall) {
        //effective dimensions on hi-dpi devices.
        float contentscale = (float) mc.getWindow().getScaleFactor();
        float width  = (int)(mc.getWindow().getFramebufferWidth() / contentscale);
        float height = (int)(mc.getWindow().getFramebufferHeight() / contentscale);
        //draw only while being ingame
        // TODO: what is causing this
        if (mc.options.hudHidden || mc.world == null || mc.options.debugEnabled)
            return;
            nvgBeginFrame(ctx, width, height, contentscale);
            //passes the current context as argument
            drawCall.accept(ctx);
            //stop drawing
            nvgEndFrame(ctx);
            //restores default mc state
            restoreState();
    }
    public static long getContext() {
        return ctx;
    }


    private static void restoreState() {
        GlStateManager._disableCull();
        GlStateManager._disableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SrcFactor.ZERO, GlStateManager.DstFactor.ONE);
    }



}
