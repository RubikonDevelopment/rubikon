package dev.rubikon.renderer.shader;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import dev.rubikon.Rubikon;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.gl.ShaderStage;
import net.minecraft.util.Identifier;

import java.io.IOException;

import static org.lwjgl.opengl.GL20.*;

public class Shader {
    private int program;
    //default path for reading the shader into a string
    private PostEffectProcessor effect;

    public Shader() {
        try {
            this.effect = new PostEffectProcessor(MinecraftClient.getInstance().getTextureManager(), MinecraftClient.getInstance().getResourceManager(), MinecraftClient.getInstance().getFramebuffer(), new Identifier("shaders/core/test.json"));
            effect.setupDimensions(MinecraftClient.getInstance().getWindow().getWidth(), MinecraftClient.getInstance().getWindow().getHeight());
            System.out.println("yes");
        } catch (IOException | JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    public PostEffectProcessor getEffect() {
        return effect;
    }
}
