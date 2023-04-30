package dev.rubikon.utils;

import lombok.experimental.UtilityClass;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;

@UtilityClass
public class ChatUtils {
    private static TextColor HIGHLIGHT_COLOR = TextColor.parse("#da2424");
    private static Text PREFIX = Text.literal("Rubikon")
            .setStyle(Style.EMPTY.withBold(true).withColor(HIGHLIGHT_COLOR));

    public static void sendMessage(String message, Object... args) {
        sendMessage(null, Text.of(format(message, args)));
    }

    public static void sendMessage(Text prefix, String message, Object... args) {
        sendMessage(prefix, Text.of(format(message, args)));
    }

    public static void sendMessage(Text prefix, Text msg) {
        MutableText message = Text.literal("");
        message.append(joinPrefix(prefix));
        message.append(msg);

        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(
                message
        );
    }

    private static MutableText joinPrefix(Text prefix) {
        MutableText message = Text.literal("");
        message.append(PREFIX);

        if (prefix == null) return message.append(" ");

        message.append(
                MutableText.of(Text.of("/").getContent())
                        .setStyle(
                                Style.EMPTY.withBold(true).withColor(Formatting.DARK_GRAY)
                        )
        );

        message.append(
                MutableText.of(prefix.getContent()).setStyle(PREFIX.getStyle())
        );

        message.append(" ");

        return message;
    }

    private static String format(String format, Object... args) {
        String msg = String.format(format, args);
        msg = msg
                .replace("(default)", Formatting.WHITE.toString())
                .replace("(highlight)", Formatting.RED.toString())
                .replace("(bold)", Formatting.BOLD.toString())
                .replace("(underline)", Formatting.UNDERLINE.toString());

        return msg;
    }
}
