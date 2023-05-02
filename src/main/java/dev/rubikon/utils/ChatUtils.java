package dev.rubikon.utils;

import dev.rubikon.Rubikon;
import lombok.experimental.UtilityClass;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;

/**
 * Utility class for managing chat messages.
 */
@UtilityClass
public class ChatUtils {
    private static final TextColor HIGHLIGHT_COLOR = TextColor.parse(Rubikon.PRIMARY_COLOR);
    private static final Text PREFIX = Text.literal("Rubikon")
            .setStyle(Style.EMPTY.withBold(true).withColor(HIGHLIGHT_COLOR));

    public static void sendMessage(String message, Object... args) {
        sendMessage(null, format(message, args));
    }

    public static void sendMessage(Text prefix, String message, Object... args) {
        sendMessage(prefix, format(message, args));
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

    public static MutableText format(String input, Object... args) {
        input = String.format(input, args);

        MutableText output = Text.literal("");
        Style currentStyle = Style.EMPTY;

        while (!input.isEmpty()) {
            int tagStart = input.indexOf("<");
            if (tagStart == -1) {
                // No more tags, add the remaining text as a plain string
                output.append(Text.literal(input).setStyle(currentStyle));
                break;
            }

            // Add the text before the tag as a plain string
            output.append(Text.literal(input.substring(0, tagStart)).setStyle(currentStyle));

            int tagEnd = input.indexOf(">");
            if (tagEnd == -1) {
                // Malformed tag, ignore it
                input = input.substring(tagStart + 1);
                continue;
            }

            String tag = input.substring(tagStart + 1, tagEnd);
            input = input.substring(tagEnd + 1);

            Style styleBuilder = currentStyle;

            for (String token : tag.split(" ")) {
                switch (token.toLowerCase()) {
                    case "bold" -> styleBuilder = styleBuilder.withBold(true);
                    case "italic" -> styleBuilder = styleBuilder.withItalic(true);
                    case "underline" -> styleBuilder = styleBuilder.withUnderline(true);
                    case "strikethrough" -> styleBuilder = styleBuilder.withStrikethrough(true);
                    case "obfuscated" -> styleBuilder = styleBuilder.withObfuscated(true);
                    case "highlight" -> styleBuilder = styleBuilder.withColor(HIGHLIGHT_COLOR);
                    default -> {
                        if (token.startsWith("#")) {
                            try {
                                styleBuilder = styleBuilder.withColor(TextColor.parse(token));
                            } catch (IllegalArgumentException ignored) {
                                // Not a valid color code, ignore it
                            }
                            break;
                        }

                        try {
                            Formatting formatting = Formatting.byName(token.toUpperCase());
                            styleBuilder = styleBuilder.withColor(formatting);
                        } catch (IllegalArgumentException ignored) {
                            // Not a recognized format code, ignore it
                        }
                    }
                }
            }

            currentStyle = styleBuilder;
        }

        return output;
    }
}
