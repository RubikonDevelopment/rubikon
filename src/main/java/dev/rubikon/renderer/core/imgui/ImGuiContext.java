package dev.rubikon.renderer.core.imgui;

import dev.rubikon.Rubikon;
import imgui.ImGui;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import net.minecraft.client.MinecraftClient;

import java.util.function.Consumer;



public class ImGuiContext {
    private static final ImGuiImplGl3 imGuiImplGl3 = new ImGuiImplGl3();
    private static final ImGuiImplGlfw imGuiImplGlfw = new ImGuiImplGlfw();
    //set to 3
    private static String shaderversion = null;
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public ImGuiContext() {}

    public static void initialize() {
        //creates imgui context
        ImGui.createContext();
        //binds imgui to minecraft window and gl context
        imGuiImplGlfw.init(mc.getWindow().getHandle(),true);
        imGuiImplGl3.init(shaderversion);
        Rubikon.LOGGER.info("ImGui was successfully initialized with opengl3 backend!");
    }

    /**
     * Draws imgui elements
     * @param drawcall Allows to draw imgui elements
     */
    public static void draw(Consumer<Void> drawcall) {
        //start drawing
        imGuiImplGlfw.newFrame();
        ImGui.newFrame();
        drawcall.accept(null);
        ImGui.render();
        imGuiImplGl3.renderDrawData(ImGui.getDrawData());
    }
}
