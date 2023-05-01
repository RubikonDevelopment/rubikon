package dev.rubikon.renderer.core;

import dev.rubikon.commons.Store;
import dev.rubikon.renderer.core.imgui.ImGuiContext;
import dev.rubikon.renderer.core.nanovg.NVContext;
import lombok.Getter;
import org.lwjgl.nanovg.NanoVG;

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

    public void init() {
        NVContext.init();
        ImGuiContext.init();

        add("sfui-bold", nvgCreateFontMem(getContext(),"sfui-bold", SFUI_BOLD, false));
        add("rubikon-icon", nvgCreateImageMem(getContext(), NVG_IMAGE_GENERATE_MIPMAPS, RUBIKON_ICON));
    }

    @Override
    public void add(String name, Integer item) {
        renderers.put(name, item);
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
