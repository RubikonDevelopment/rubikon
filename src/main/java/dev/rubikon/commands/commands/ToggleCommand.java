package dev.rubikon.commands.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.rubikon.api.commands.Command;
import dev.rubikon.api.feature.Feature;
import dev.rubikon.commands.arguments.FeatureArgumentType;
import dev.rubikon.utils.ChatUtils;
import net.minecraft.command.CommandSource;

import static com.mojang.brigadier.Command.SINGLE_SUCCESS;

public class ToggleCommand extends Command {
    public ToggleCommand() {
        super("toggle", "Toggles a feature", "t");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        builder.then(argument("feature", FeatureArgumentType.create()).executes(context -> {
            Feature feature = context.getArgument("feature", Feature.class);

            feature.toggle();
            return SINGLE_SUCCESS;
        }));
    }
}
