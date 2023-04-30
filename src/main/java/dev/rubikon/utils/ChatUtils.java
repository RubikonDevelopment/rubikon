package dev.rubikon.utils;

import lombok.experimental.UtilityClass;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;

@UtilityClass
public class ChatUtils {
    public static void sendMessage(Text message) {
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(
                message
        );
    }
}
