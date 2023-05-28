package dev.rubikon.things.features.misc;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.rubikon.events.ScreenRenderEvent;
import dev.rubikon.renderer.core.Renderer;
import dev.rubikon.renderer.core.nanovg.NVContext;
import dev.rubikon.things.features.Categories;
import dev.rubikon.things.features.Feature;
import io.github.nevalackin.radbus.Listen;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.SimpleFramebuffer;
import net.minecraft.client.render.*;
import org.joml.Matrix4f;
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
        super("Logo","Lets you toggle rendering of the client logo", Categories.MISC, GLFW.GLFW_KEY_L);
    }
    private int fbohandle;
    private SimpleFramebuffer framebuffer;

    @Override
    public void onEnable() {
        framebuffer = new SimpleFramebuffer(mc.getWindow().getWidth(),mc.getWindow().getHeight(),false,false);

        //Renderer.getShader().getEffect().setupDimensions(mc.getWindow().getWidth(),mc.getWindow().getHeight());
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> {
            //Renderer.getShader().getEffect().render(tickDelta);

            Matrix4f positionMatrix = matrixStack.peek().getPositionMatrix();
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();

            buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION);
            buffer.vertex(positionMatrix, 20, 20, 0).next();
            buffer.vertex(positionMatrix, 20, 60, 0).next();
            buffer.vertex(positionMatrix, 60, 60, 0).next();
            buffer.vertex(positionMatrix, 60, 20, 0).next();

            RenderSystem.setShader(() -> Renderer.getInstance().getTestProgram());

            tessellator.draw();
        });
    }

    @Listen
    public void onRender(ScreenRenderEvent event) {
        //int image = Renderer.getInstance().find("rubikon-icon");
        // TODO shader for text

//        //render the text
//        NVContext.draw(ctx -> {
//            createHandle();
//            //alloc memory
//            //TODO implement rasterizer
//            try (MemoryStack stack = MemoryStack.stackPush()) {
//                //get default image size
//                IntBuffer pW = stack.mallocInt(1);
//                IntBuffer pH = stack.mallocInt(1);
//                //set it
//                nvgImageSize(ctx, image, pW, pH);
//                //clear paths
//                nvgBeginPath(ctx);
//                nvgRect(ctx, 2, 4, 25, 25);
//                NVGPaint logoPaint = NVGPaint.create();
//                //creates fbo as a image and binds it onto the paint
//                nvgImagePattern(ctx, 0.5f, 1, 30, 30, 0.f, fbohandle, 1.f, logoPaint);
//                nvgFillPaint(ctx, logoPaint);
//                nvgFill(ctx);
//            }
//            nvgFontFace(ctx,"sfui-bold");
//            nvgFontSize(ctx,24.f);
//            NVGColor fillcolor = nvgColor(-1);
//            nvgFillColor(ctx,fillcolor);
//            //draw text
//            nvgText(ctx,25,25,"Rubikon");
//        });
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


}