package dev.rubikon.things.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.rubikon.things.commands.Command;
import net.minecraft.command.CommandSource;

public class FeatureCommand extends Command {
    public FeatureCommand() {
        super("feature", "feature", "feature", "feature", "feature");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> builder) {
    }
}
