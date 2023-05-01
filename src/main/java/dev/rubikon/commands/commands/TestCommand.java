package dev.rubikon.commands.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.rubikon.api.commands.Command;
import dev.rubikon.utils.ChatUtils;
import net.minecraft.command.CommandSource;

import static com.mojang.brigadier.Command.SINGLE_SUCCESS;

public class TestCommand extends Command {
    public TestCommand() {
        super("test", "test command", "rubikontest");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        builder.then(argument("test", StringArgumentType.string()).executes(context -> {
            String test = StringArgumentType.getString(context, "test");

            ChatUtils.sendMessage("test: " + test);

            return SINGLE_SUCCESS;
        }));
    }
}
