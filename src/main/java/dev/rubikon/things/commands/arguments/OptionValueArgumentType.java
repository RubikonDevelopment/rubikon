package dev.rubikon.things.commands.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import dev.rubikon.settings.Option;
import net.minecraft.command.CommandSource;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class OptionValueArgumentType implements ArgumentType<String> {
    public static OptionValueArgumentType create() {
        return new OptionValueArgumentType();
    }

    /**
     * @see StringArgumentType#greedyString()
     * @see StringArgumentType#parse(StringReader)
     */
    @Override
    public String parse(StringReader reader) throws CommandSyntaxException {
        final String text = reader.getRemaining();
        reader.setCursor(reader.getTotalLength());
        return text;
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        Option<?> option;

        try {
            option = OptionArgumentType.get(context);
        } catch (CommandSyntaxException e) {
            return null;
        }

        Iterable<Identifier> identifierSuggestions = option.commandIdentifierSuggestions();
        if (identifierSuggestions != null)
            return CommandSource.suggestIdentifiers(identifierSuggestions, builder);

        return CommandSource.suggestMatching(option.commandSuggestions(), builder);
    }
}
