package dev.rubikon.mixin;

import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.suggestion.Suggestions;
import dev.rubikon.things.commands.Commands;
import net.minecraft.client.gui.screen.ChatInputSuggestor;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.command.CommandSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.concurrent.CompletableFuture;

@Mixin(ChatInputSuggestor.class)
public abstract class ChatInputSuggestorMixin {
    @Shadow private ParseResults<CommandSource> parse;
    @Shadow @Final TextFieldWidget textField;
    @Shadow boolean completingSuggestions;
    @Shadow private CompletableFuture<Suggestions> pendingSuggestions;
    @Shadow private ChatInputSuggestor.SuggestionWindow window;

    @Shadow
    protected abstract void showCommandSuggestions();

    @Inject(
            method = "refresh",
            at = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/StringReader;canRead()Z", remap = false),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    public void onRefresh(CallbackInfo ci, String string, StringReader reader) {
        if (!reader.canRead(1) || !reader.getString().startsWith(".", reader.getCursor())) return;

        reader.setCursor(reader.getCursor() + 1);

        if (this.parse == null)
            this.parse = Commands.DISPATCHER.parse(reader, Commands.COMMAND_SOURCE);

        int cursor = this.textField.getCursor();
        if (cursor >= 1 && (this.window == null || !this.completingSuggestions)) {
            this.pendingSuggestions = Commands.DISPATCHER.getCompletionSuggestions(this.parse, cursor);
            this.pendingSuggestions.thenRun(() -> {
                if (this.pendingSuggestions.isDone())
                    this.showCommandSuggestions();
            });
        }

        ci.cancel();
    }
}
