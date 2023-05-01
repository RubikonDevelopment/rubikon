package dev.rubikon.stores;

import dev.rubikon.api.stores.Store;
import dev.rubikon.utils.ResourceUtils;

import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.nanovg.NanoVG.*;
import static dev.rubikon.renderer.core.nanovg.NVContext.*;

public class TextRendererStore extends Store<String,Integer> {

    private final ByteBuffer SFUI_BOLD = load("assets/rubikon/fonts/SFUI-Bold.ttf");

    public void init() {
        add("sfui-bold",nvgCreateFontMem(getContext(),"sfui-bold",SFUI_BOLD,false));
    }

    private ByteBuffer load(String resource){
            try {
                return ResourceUtils.ioResourceToByteBuffer(resource, 150 * 1024);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load resource: " + resource, e);
            }
    }
}
