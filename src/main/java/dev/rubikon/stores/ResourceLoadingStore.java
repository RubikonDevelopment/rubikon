package dev.rubikon.stores;

import dev.rubikon.api.commons.Store;

import java.nio.ByteBuffer;

import static dev.rubikon.utils.ResourceUtils.*;
import static org.lwjgl.nanovg.NanoVG.*;
import static dev.rubikon.renderer.core.nanovg.NVContext.*;

public class ResourceLoadingStore extends Store<String,Integer> {

    private final ByteBuffer SFUI_BOLD = load("assets/rubikon/fonts/SFUI-Bold.ttf");
    private final ByteBuffer RUBIKON_ICON = loadImage("assets/rubikon/icon.png");


    public void init() {
        add("sfui-bold",nvgCreateFontMem(getContext(),"sfui-bold",SFUI_BOLD,false));
        add("rubikon-icon",nvgCreateImageMem(getContext(),NVG_IMAGE_GENERATE_MIPMAPS,RUBIKON_ICON));
    }


}
