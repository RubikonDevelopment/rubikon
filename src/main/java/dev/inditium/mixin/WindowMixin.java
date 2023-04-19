package dev.inditium.mixin;

import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_ANY_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_CORE_PROFILE;

@Mixin(Window.class)
public class WindowMixin {
    @ModifyConstant(method = "<init>", constant = @Constant(intValue = GLFW_OPENGL_CORE_PROFILE))
    private static int getOpenGLProfile(int constant) {
        return GLFW_OPENGL_ANY_PROFILE;
    }
}
