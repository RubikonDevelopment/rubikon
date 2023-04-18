package dev.inditium.renderer.nanvog.core;


import dev.inditium.Inditium;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.nanovg.NanoVGGL3;
import static org.lwjgl.system.MemoryUtil.NULL;


public class NVContext {
    private static float ctx = 0;
    public NVContext() {}

    public static void initialize() {
        //create nanovg context for current thread with antialiasing
        ctx = NanoVGGL3.nvgCreate(NanoVGGL3.NVG_ANTIALIAS);

        if (ctx == NULL) {
            Inditium.LOGGER.error("NVContext#initialize: Couldn't init NanoVG",new RuntimeException());
        }

        Inditium.LOGGER.info("NanoVG was succesfully intialized with opengl3 backend!");
    }

    public static float getCTX() {
        return ctx;
    }
}
