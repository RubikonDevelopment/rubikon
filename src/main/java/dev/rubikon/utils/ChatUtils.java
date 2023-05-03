package dev.rubikon.utils;

import dev.rubikon.Rubikon;
import lombok.experimental.UtilityClass;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents a utility class for chat-related operations.
 */
@UtilityClass
public class ChatUtils {
    private static final TextColor HIGHLIGHT_COLOR = TextColor.parse(Rubikon.PRIMARY_COLOR);
    private static final Text PREFIX = Text.literal("Rubikon")
            .setStyle(Style.EMPTY.withBold(true).withColor(HIGHLIGHT_COLOR));

    /**
     * Sends a message to the chat.
     * @param message The message to send.
     * @param args The arguments to format the message with.
     */
    public static void sendMessage(String message, Object... args) {
        sendMessage(null, format(message, args));
    }

    /**
     * Sends a message to the chat.
     * @param prefix The sub-prefix to use for the message.
     * @param message The message to send.
     * @param args The arguments to format the message with.
     */
    public static void sendMessage(Text prefix, String message, Object... args) {
        sendMessage(prefix, format(message, args));
    }

    /**
     * Sends a message to the chat.
     * @param prefix The sub-prefix to use for the message.
     * @param msg The message to send.
     */
    public static void sendMessage(Text prefix, Text msg) {
        MutableText message = Text.literal("");
        message.append(joinPrefix(prefix));
        message.append(msg);

        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(
                message
        );
    }

    @ApiStatus.Internal
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

    /**
     * Formats a string with tags.
     * <p>
     *     Tags are formatted as follows:
     *     <ul>
     *         <li><code>&lt;bold&gt;</code> - Makes the text bold.</li>
     *         <li><code>&lt;italic&gt;</code> - Makes the text italic.</li>
     *         <li><code>&lt;underline&gt;</code> - Makes the text underlined.</li>
     *         <li><code>&lt;strikethrough&gt;</code> - Makes the text strikethrough.</li>
     *         <li><code>&lt;obfuscated&gt;</code> - Makes the text obfuscated.</li>
     *         <li><code>&lt;highlight&gt;</code> - Makes the text highlighted {@link #HIGHLIGHT_COLOR}.</li>
     *         <li><code>&lt;#RRGGBB&gt;</code> - Makes the text colored with the specified hex color.</li>
     *         <li><code>&lt;COLOR&gt;</code> - Makes the text colored with the specified color code.</li>
     * </p>
     * @param input The input string.
     * @param args The arguments to format the string with.
     * @return The formatted string as a {@link MutableText}.
     */
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
