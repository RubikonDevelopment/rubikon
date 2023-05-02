package dev.rubikon.things.features.visual;

import dev.rubikon.events.ScreenRenderEvent;
import dev.rubikon.renderer.core.Renderer;
import dev.rubikon.renderer.core.nanovg.NVContext;
import dev.rubikon.things.features.Feature;
import dev.rubikon.things.features.FeatureCategory;
import io.github.nevalackin.radbus.Listen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.SimpleFramebuffer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NVGPaint;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import static org.lwjgl.nanovg.NanoVG.*;
import static org.lwjgl.nanovg.NanoVGGL3.nvglCreateImageFromHandle;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glViewport;


public class Logo extends Feature {
    public Logo() {
        super("Logo","Lets you toggle rendering of the client logo", GLFW.GLFW_KEY_L, FeatureCategory.VISUAL);
    }
    private int fbohandle;
    private SimpleFramebuffer framebuffer;

    @Override
    public void onEnable() {
        framebuffer = new SimpleFramebuffer(mc.getWindow().getWidth(),mc.getWindow().getHeight(),false,false);
    }

    @Listen
    public void onRender(ScreenRenderEvent event) {
        int image = Renderer.getInstance().find("rubikon-icon");
        // TODO shader for text
        drawtofbo();
        //render the text
        NVContext.draw(ctx -> {
            createHandle();
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
                nvgImagePattern(ctx, 0.5f, 1, 30, 30, 0.f, fbohandle, 1.f, logoPaint);
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

    public void createHandle() {
        if (fbohandle != 0) return;

        fbohandle = nvglCreateImageFromHandle(NVContext.getContext(),framebuffer.getColorAttachment(),framebuffer.textureWidth,framebuffer.textureHeight,NVG_IMAGE_FLIPY);
    }

    public void drawtofbo() {
        if (this.fbohandle != 0) return;
        int fbowidth = this.framebuffer.textureWidth;
        int fboheight = this.framebuffer.textureHeight;
        glViewport(0, 0, fbowidth, fboheight);
        this.framebuffer.beginWrite(false);
        Renderer.getInstance().shader.bind();
        Renderer.getInstance().shader.uniform2f("resolution",fbowidth,fboheight);
        double seconds = System.nanoTime() / (1_000_000.0 * 1_000.0);
        float fSecs = (float) (seconds % Float.MAX_VALUE);
        Renderer.getInstance().shader.uniform1f("time",fSecs);
        glBegin(GL_QUADS);
        {
            glVertex2f(0.f, 0.f);
            glVertex2f(0.f, mc.getWindow().getHeight());
            glVertex2f(mc.getWindow().getScaledWidth(), mc.getWindow().getHeight());
            glVertex2f(mc.getWindow().getScaledWidth(), 0.f);
        }
        glEnd();

        // stop using shader
        Renderer.getInstance().shader.unbind();
        MinecraftClient.getInstance().getFramebuffer().beginWrite(true);
    }
}