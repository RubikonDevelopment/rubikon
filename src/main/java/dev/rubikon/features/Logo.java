package dev.rubikon.features;

import dev.rubikon.Rubikon;
import dev.rubikon.api.feature.Feature;
import dev.rubikon.events.ScreenRenderEvent;
import dev.rubikon.renderer.core.nanovg.NVContext;
import dev.rubikon.stores.Stores;
import dev.rubikon.utils.ResourceUtils;
import io.github.nevalackin.radbus.Listen;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NVGPaint;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import static org.lwjgl.nanovg.NanoVG.*;

public class Logo extends Feature {

    private final int imagehandle;

    public Logo() {
        super("Logo","Lets you toggle rendering of the client logo", GLFW.GLFW_KEY_L);
        //get image
        this.imagehandle = Stores.RESOURCE.get("rubikon-icon");
    }



    @Override
    public void onEnable() {

    }

    @Listen
    public void onRender(ScreenRenderEvent event) {
        //render the text
        NVContext.draw(ctx -> {
            //alloc memory
            //TODO implement rasterizer
            try (MemoryStack stack = MemoryStack.stackPush()) {
              //get default image size
              IntBuffer pW = stack.mallocInt(1);
              IntBuffer pH = stack.mallocInt(1);
              //set it
              nvgImageSize(ctx, imagehandle, pW, pH);
              //clear paths
              nvgBeginPath(ctx);
              nvgRect(ctx, 2, 4, 25, 25);
              NVGPaint logoPaint = NVGPaint.create();
              //creates fbo as a image and binds it onto the paint
              nvgImagePattern(ctx, 0.5f, 1, 30, 30, 0.f, imagehandle, 1.f, logoPaint);
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
