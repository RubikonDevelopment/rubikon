package dev.rubikon.renderer.core;

import dev.rubikon.renderer.shader.Shader;
import dev.rubikon.utils.Store;
import dev.rubikon.renderer.core.imgui.ImGuiContext;
import dev.rubikon.renderer.core.nanovg.NVContext;
import lombok.Getter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;

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

    public Shader shader;

    public Renderer() {
        instance = this;
    }

    public void init() {
        NVContext.init();
        ImGuiContext.init();

        add("sfui-bold", nvgCreateFontMem(getContext(),"sfui-bold", SFUI_BOLD, false));
        add("rubikon-icon", nvgCreateImageMem(getContext(), NVG_IMAGE_GENERATE_MIPMAPS, RUBIKON_ICON));
        shader = new Shader("#version 130\n" +
                "\n" +
                "uniform vec2 resolution;\n" +
                "uniform float time;\n" +
                "uniform vec3 color;\n" +
                "\n" +
                "const float Pi = 3.14159;\n" +
                "\n" +
                "const int   complexity      = 35;    // More points of color.\n" +
                "const float fluid_speed     = 1.5;  // Drives speed, higher number will make it slower.\n" +
                "const float color_intensity = 100.0;\n" +
                "\n" +
                "void main()\n" +
                "{\n" +
                "    vec2 p = (2.0 * gl_FragCoord.xy - resolution) / max(resolution.x, resolution.y);\n" +
                "    for (int i = 1;i < complexity; i++)\n" +
                "    {\n" +
                "        vec2 newp = p + time * 0.001;\n" +
                "        newp.x += 0.6 / float(i) * sin(float(i) * p.y + time / fluid_speed + 0.3 * float(i)) + 0.5; // + mouse.y/mouse_factor+mouse_offset;\n" +
                "        newp.y += 0.6 / float(i) * sin(float(i) * p.x + time / fluid_speed + 0.3 * float(i + 10)) - 0.5; // - mouse.x/mouse_factor+mouse_offset;\n" +
                "        p = newp;\n" +
                "    }\n" +
                "\n" +
                "    // Change the mix ratio to increase the marble feel and change the white color to a light blue color\n" +
                "    float mix_ratio = 0.4 * sin(3.0 * p.x) + 0.6;\n" +
                "    vec3 col = mix(color, vec3(0.6, 0.9, 1.0), mix_ratio);\n" +
                "    gl_FragColor = vec4(col, 1.0);\n" +
                "}\n");
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
