package dev.rubikon.things.features.misc;

import dev.rubikon.events.ScreenRenderEvent;
import dev.rubikon.renderer.core.Renderer;
import dev.rubikon.renderer.core.nanovg.NVContext;
import dev.rubikon.things.features.Categories;
import dev.rubikon.things.features.Feature;
import io.github.nevalackin.radbus.Listen;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NVGPaint;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import static org.lwjgl.nanovg.NanoVG.*;

public class Logo extends Feature {
    public Logo() {
        super("Logo","Lets you toggle rendering of the client logo", Categories.MISC, GLFW.GLFW_KEY_L);
    }

    @Override
    public void onEnable() {

    }

    @Listen
    public void onRender(ScreenRenderEvent event) {
        int image = Renderer.getInstance().find("rubikon-icon");

        //render the text
        NVContext.draw(ctx -> {
            //alloc memory
            //TODO implement rasterizer
            try (MemoryStack stack = MemoryStack.stackPush()) {
                //get default image size
                IntBuffer pW = stack.mallocInt(1);
                IntBuffer pH = stack.mallocInt(1);
                //set it
                nvgImageSize(ctx, image, pW, pH);
                //clear paths
                nvgBeginPath(ctx);
                nvgRect(ctx, 2, 4, 25, 25);
                NVGPaint logoPaint = NVGPaint.create();
                //creates fbo as a image and binds it onto the paint
                nvgImagePattern(ctx, 0.5f, 1, 30, 30, 0.f, image, 1.f, logoPaint);
                nvgFillPaint(ctx, logoPaint);
                nvgFill(ctx);
            }
            nvgFontFace(ctx,"sfui-bold");
            nvgFontSize(ctx,24.f);
            NVGColor fillcolor = nvgColor(-1);
            nvgFillColor(ctx,fillcolor);
            //draw text
            nvgText(ctx,25,25,"Rubikon");
        });
    }

    public static NVGColor nvgColor(int argb) {
        NVGColor color = NVGColor.create();
        nvgRGBA((byte) (argb >> 16 & 255), (byte) (argb >> 8 & 255), (byte) (argb >> 0 & 255), (byte) (argb >> 24 & 255), color);
        return color;
    }

    @Override
    public void onDisable() {

    }
}