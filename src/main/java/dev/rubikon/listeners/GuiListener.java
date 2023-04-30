package dev.rubikon.listeners;

import dev.rubikon.events.ScreenRenderEvent;
import dev.rubikon.renderer.core.imgui.ImGuiContext;
import imgui.ImGui;
import io.github.nevalackin.radbus.Listen;

public class GuiListener {
    @Listen
    public void onScreenRender(ScreenRenderEvent event) {
        ImGuiContext.draw(call ->{
            ImGui.showDemoWindow();
        });
    }
}
