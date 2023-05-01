package dev.rubikon.features;

import dev.rubikon.api.feature.Feature;
import dev.rubikon.events.ScreenRenderEvent;
import dev.rubikon.renderer.core.nanovg.NVContext;
import io.github.nevalackin.radbus.Listen;
import org.lwjgl.glfw.GLFW;

import static dev.rubikon.renderer.core.nanovg.NVContext.nvgColor;
import static org.lwjgl.nanovg.NanoVG.*;

public class Logo extends Feature {
    public Logo() {
        super("Logo","Lets you toggle rendering of the client logo", GLFW.GLFW_KEY_L);
    }

    @Override
    public void onEnable() {

    }

    @Listen
    public void onRender(ScreenRenderEvent event) {
        //render the text
        NVContext.draw(vg -> {
//            nvgFontFace(ctx,"sfui-bold");
//            nvgFontSize(ctx,24.f);
//            NVGColor fillcolor = NVContext.nvgColor(-1);
//            nvgFillColor(ctx,fillcolor);
//            nvgText(ctx,40,20,"Rubikon");
            nvgBeginPath(vg);
            nvgRect(vg, 10, 10, 100, 1000);
            nvgFillColor(vg, nvgColor(-1));
            nvgFill(vg);
            nvgClosePath(vg);
        });
    }

    @Override
    public void onDisable() {

    }
}
