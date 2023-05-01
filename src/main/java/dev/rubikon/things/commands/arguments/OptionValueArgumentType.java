package dev.rubikon.things.commands.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import dev.rubikon.settings.Option;
import dev.rubikon.settings.types.*;

import java.util.concurrent.CompletableFuture;

public class OptionValueArgumentType implements ArgumentType<String> {
    public static OptionValueArgumentType create() {
        return new OptionValueArgumentType();
    }

    @Override
    public String parse(StringReader reader) throws CommandSyntaxException {
        String text = reader.readString();
        reader.setCursor(reader.getTotalLength());
        return text;
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        try {
            Option<?> option = OptionArgumentType.get(context);
            if (option instanceof BlockOption)
                return StringArgumentType.string().listSuggestions(context, builder);
            else if (option instanceof BoolOption)
                return BoolArgumentType.bool().listSuggestions(context, builder);
            else if (option instanceof ColorOption)
                return StringArgumentType.string().listSuggestions(context, builder);
            else if (option instanceof DoubleOption)
                return DoubleArgumentType.doubleArg().listSuggestions(context, builder);
            else if (option instanceof IntOption)
                return IntegerArgumentType.integer(((IntOption) option).getMin(), ((IntOption) option).getMax())
                        .listSuggestions(context, builder);
            else if (option instanceof ItemOption)
                return StringArgumentType.string().listSuggestions(context, builder);
            else if (option instanceof StringOption)
                return StringArgumentType.string().listSuggestions(context, builder);
            else
                return StringArgumentType.string().listSuggestions(context, builder);
        } catch (CommandSyntaxException e) {}

        return StringArgumentType.string().listSuggestions(context, builder);
    }
}
