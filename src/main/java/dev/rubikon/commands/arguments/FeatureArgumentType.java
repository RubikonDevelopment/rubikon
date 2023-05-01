package dev.rubikon.commands.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import dev.rubikon.api.feature.Feature;
import dev.rubikon.stores.Stores;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class FeatureArgumentType implements ArgumentType<Feature> {
    private static final DynamicCommandExceptionType NO_SUCH_FEATURE = new DynamicCommandExceptionType(name -> Text.literal("Unable to find feature " + name));

    private static final Collection<String> EXAMPLES = Stores.FEATURE.keys().stream().limit(3).toList();

    public static FeatureArgumentType create() {
        return new FeatureArgumentType();
    }

    @Override
    public Feature parse(StringReader reader) throws CommandSyntaxException {
        String name = reader.readString();
        Feature feature = Stores.FEATURE.get(name);

        if (feature == null)
            throw NO_SUCH_FEATURE.create(name);

        return feature;
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return CommandSource.suggestMatching(Stores.FEATURE.keys(), builder);
    }

    @Override
    public Collection<String> getExamples() {
        return EXAMPLES;
    }
}
