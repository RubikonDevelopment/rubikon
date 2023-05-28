package dev.rubikon.renderer.core.imgui;

import dev.rubikon.Rubikon;
import imgui.ImGui;
import imgui.flag.ImGuiCol;
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

    public static void init() {
        //creates imgui context
        ImGui.createContext();
        //binds imgui to minecraft window and gl context
        imGuiImplGlfw.init(mc.getWindow().getHandle(),true);
        imGuiImplGl3.init(shaderversion);
        Rubikon.LOGGER.info("ImGui was successfully initialized with opengl3 backend!");
        //style imgui
        ImGui.getStyle().setWindowRounding(9);
        ImGui.getStyle().setWindowTitleAlign(0.5f,0.5f);
        //set colors
        //TODO FIX COLORS
//        ImGui.getStyle().setColor(ImGuiCol.FrameBgHovered,0xB70E0EFF);
//        ImGui.getStyle().setColor(ImGuiCol.FrameBgActive,0x710606FF);
//        ImGui.getStyle().setColor(ImGuiCol.TitleBgActive,0xB70E0EFF);
//        ImGui.getStyle().setColor(ImGuiCol.CheckMark,0x950909FF);
//        ImGui.getStyle().setColor(ImGuiCol.SliderGrab,0xB70E0EFF);
//        ImGui.getStyle().setColor(ImGuiCol.SliderGrabActive,0x910C0CFF);
//        ImGui.getStyle().setColor(ImGuiCol.Button,0x981818FF);
//        ImGui.getStyle().setColor(ImGuiCol.ButtonHovered,0x4C0303FF);
//        ImGui.getStyle().setColor(ImGuiCol.ButtonActive,0x380202FF);
//        ImGui.getStyle().setColor(ImGuiCol.Header,0xB70E0EFF);
//        ImGui.getStyle().setColor(ImGuiCol.HeaderActive,0xB70E0EFF);
//        ImGui.getStyle().setColor(ImGuiCol.SeparatorHovered,0xB70E0EFF);
//        ImGui.getStyle().setColor(ImGuiCol.SeparatorActive,0xB70E0EFF);
//        ImGui.getStyle().setColor(ImGuiCol.ResizeGrip,0xB70E0EFF);
//        ImGui.getStyle().setColor(ImGuiCol.ResizeGripActive,0x790606FF);
//        ImGui.getStyle().setColor(ImGuiCol.ResizeGripHovered,0xB70E0EFF);
//        ImGui.getStyle().setColor(ImGuiCol.Tab,0xB70E0EFF);
//        ImGui.getStyle().setColor(ImGuiCol.TabHovered,0x8B0505FF);
//        ImGui.getStyle().setColor(ImGuiCol.TabActive,0x6E0808FF);
//        ImGui.getStyle().setColor(ImGuiCol.DockingPreview,0xB70E0EFF);
//        ImGui.getStyle().setColor(ImGuiCol.DockingPreview,0xB70E0EFF);
//        ImGui.getStyle().setColor(ImGuiCol.DockingPreview,0xB70E0EFF);
//        ImGui.getStyle().setColor(ImGuiCol.NavHighlight,0xB70E0EFF);

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
