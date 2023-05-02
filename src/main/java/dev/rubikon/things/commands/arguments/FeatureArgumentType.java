package dev.rubikon.things.commands.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import dev.rubikon.things.features.Feature;
import dev.rubikon.things.features.Features;
import dev.rubikon.utils.ChatUtils;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class FeatureArgumentType implements ArgumentType<Feature> {
    private static final DynamicCommandExceptionType NO_SUCH_FEATURE = new DynamicCommandExceptionType(name -> ChatUtils.format("Unable to find feature <highlight>%s<white>.", name));

    private static final Collection<String> EXAMPLES = Features.get().names().stream().limit(3).toList();

    public static FeatureArgumentType create() {
        return new FeatureArgumentType();
    }

    @Override
    public Feature parse(StringReader reader) throws CommandSyntaxException {
        String name = reader.readString();
        Feature feature = Features.get().find(name);

        if (feature == null)
            throw NO_SUCH_FEATURE.create(name);

        return feature;
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return CommandSource.suggestMatching(Features.get().names(), builder);
    }

    @Override
    public Collection<String> getExamples() {
        return EXAMPLES;
    }
}
