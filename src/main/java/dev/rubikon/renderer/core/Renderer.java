package dev.rubikon.renderer.core;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.rubikon.renderer.shader.Shader;
import dev.rubikon.utils.Store;
import dev.rubikon.renderer.core.imgui.ImGuiContext;
import dev.rubikon.renderer.core.nanovg.NVContext;
import lombok.Getter;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.*;
import org.joml.Matrix4f;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.HashMap;

import static dev.rubikon.renderer.core.nanovg.NVContext.getContext;
import static dev.rubikon.utils.ResourceUtils.load;
import static dev.rubikon.utils.ResourceUtils.loadImage;
import static org.lwjgl.nanovg.NanoVG.*;

public class Renderer implements Store<String, Integer> {
    @Getter
    private static Renderer instance;
    private final ByteBuffer SFUI_BOLD = load("assets/rubikon/fonts/SFUI-Bold.ttf");
    private final ByteBuffer RUBIKON_ICON = loadImage("assets/rubikon/icon.png");
    private final HashMap<String, Integer> renderers = new HashMap<>();


    public Renderer() {
        instance = this;
    }
    private static Shader shader;
    public void init() {
        NVContext.init();
        ImGuiContext.init();
        shader = new Shader();

        add("sfui-bold", nvgCreateFontMem(getContext(),"sfui-bold", SFUI_BOLD, false));
        add("rubikon-icon", nvgCreateImageMem(getContext(), NVG_IMAGE_GENERATE_MIPMAPS, RUBIKON_ICON));
    }

    @Override
    public void add(String name, Integer item) {
        renderers.put(name, item);
    }

    public static Shader getShader() {
        return shader;
    }

    @Override
    public Integer find(String name) {
        return renderers.get(name);
    }

    @Override
    public Collection<String> names() {
        return renderers.keySet();
    }

    @Override
    public Collection<Integer> all() {
        return renderers.values();
    }
}
