package dev.rubikon.things.commands.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import dev.rubikon.settings.Option;
import dev.rubikon.things.features.Feature;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

import java.util.concurrent.CompletableFuture;

public class OptionArgumentType implements ArgumentType<String> {
    private static final DynamicCommandExceptionType NO_SUCH_OPTION = new DynamicCommandExceptionType(name -> Text.literal("Unable to find option " + name));

    public static OptionArgumentType create() {
        return new OptionArgumentType();
    }

    public static Option<?> get(CommandContext<?> context) throws CommandSyntaxException {
        Feature feature = context.getArgument("feature", Feature.class);
        String name = context.getArgument("option", String.class);

        Option<?> option = feature.getOptions().findOption(name);

        if (option == null)
            throw NO_SUCH_OPTION.create(name);

        return option;
    }

    @Override
    public String parse(StringReader reader) throws CommandSyntaxException {
        // We can't because we don't have a reference to the feature
        // TODO: use suggestions instead argument type
        return reader.readString();
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return CommandSource.suggestMatching(
                context.getArgument("feature", Feature.class).getOptions().optionNames(),
                builder
        );
    }
}
