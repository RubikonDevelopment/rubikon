package dev.inditium.renderer.nanvog.core;


import dev.inditium.Inditium;
import net.minecraft.client.MinecraftClient;

import static java.lang.Math.*;
import static org.lwjgl.nanovg.NanoVG.*;
import static org.lwjgl.nanovg.NanoVGGL3.*;

import java.util.function.Consumer;

import static org.lwjgl.system.MemoryUtil.NULL;


public class NVContext {
    private static long ctx = 0;
    private static final MinecraftClient mc = MinecraftClient.getInstance();
    public NVContext() {}

    public static void initialize() {
        //create nanovg context for current thread with antialiasing
        ctx = nvgCreate(NVG_ANTIALIAS);

        if (ctx == NULL) {
            //opengl 3 must be supported
            Inditium.LOGGER.error("NVContext#initialize: Couldn't init NanoVG",new RuntimeException());
        }
        Inditium.LOGGER.info("NanoVG was succesfully intialized with opengl3 backend!");
    }

    public static void draw(Consumer<Long> drawCall) {
        //effective dimensions on hi-dpi devices.
        float contentscale = (float) mc.getWindow().getScaleFactor();
        float width  = (int)(mc.getWindow().getFramebufferWidth() / contentscale);
        float height = (int)(mc.getWindow().getFramebufferHeight() / contentscale);
        nvgBeginFrame(ctx, width, height, contentscale);
        //passes the current context as argument
        drawCall.accept(ctx);
        //stop drawing
        nvgEndFrame(ctx);
    }
}
