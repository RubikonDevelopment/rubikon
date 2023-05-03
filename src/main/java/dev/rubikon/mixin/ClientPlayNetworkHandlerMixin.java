package dev.rubikon.mixin;

import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.rubikon.Rubikon;
import dev.rubikon.events.NetworkEvents;
import dev.rubikon.things.commands.Commands;
import dev.rubikon.utils.ChatUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "onGameJoin", at = @At("RETURN"))
    public void onGameJoin(GameJoinS2CPacket packet, CallbackInfo ci) {
        Rubikon.getEventPubSub().publish(new NetworkEvents.JoinGameEvent(packet));
    }

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    private void onSendChatMessage(String message, CallbackInfo ci) {
        if (!message.startsWith(".")) return;
        final String command = message.substring(1);

        try {
            Commands.get().dispatch(command);
        } catch (CommandSyntaxException e) {
            ChatUtils.sendMessage(Text.of("Commands"), getMessageAsText(command, e));
        }

        client.inGameHud.getChatHud().addToMessageHistory(message);
        ci.cancel();
    }

    private MutableText getMessageAsText(String command, CommandSyntaxException exception) {
        MutableText message = Text.literal("");
        message.append((MutableText) exception.getRawMessage());

        if (exception.getInput() != null && exception.getCursor() >= 0) {
            message.append("\n");
            message.append(cursorMessage(command, exception));
        }

        return message;
    }

    /**
     * From {@link net.minecraft.server.command.CommandManager#execute(ParseResults, String)}
     */
    private MutableText cursorMessage(String command, CommandSyntaxException exception) {
        int i = Math.min(exception.getInput().length(), exception.getCursor());
        MutableText mutableText = Text.empty().styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "." + command)));
        if (i > 10) {
            mutableText.append(ScreenTexts.ELLIPSIS);
        }
        mutableText.append(exception.getInput().substring(Math.max(0, i - 10), i));
        mutableText.append(Text.translatable("command.context.here").formatted(Formatting.ITALIC));

        if (i < exception.getInput().length()) {
            mutableText.append("\n");
            MutableText text = Text.literal(exception.getInput().substring(i)).formatted(Formatting.UNDERLINE);
            mutableText.append(text);
        }

        return mutableText;
    }
}
