package dev.rubikon.things.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import lombok.Getter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a base class for all commands.
 * <p>
 *     This class is used to register commands and their arguments.
 *     It also provides a few helper methods to make the process easier.
 *     <br>
 *     <br>
 *     To create a new command, you need to extend this class and implement the {@link #build(LiteralArgumentBuilder)} method.
 *     <br>
 *     <br>
 *     To register a command, you need to call the {@link #register(CommandDispatcher)} method.
 * </p>
 *
 * @see LiteralArgumentBuilder
 * @see Commands
 */
public abstract class Command {
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final List<String> aliases = new ArrayList<>();

    protected MinecraftClient mc = MinecraftClient.getInstance();

    protected Command(String name, String description, String... aliases) {
        this.name = name;
        this.description = description;
        Collections.addAll(this.aliases, aliases);
    }

    /**
     * Builds the command.
     * @param builder The command builder to build the command with.
     */
    public abstract void build(LiteralArgumentBuilder<CommandSource> builder);

    /**
     * Helper method to create a new argument.
     * @param name The name of the argument. This is used to identify the argument.
     * @param type The type of the argument.
     * @return a new {@link RequiredArgumentBuilder} instance.
     */
    protected static <T> RequiredArgumentBuilder<CommandSource, T> argument(final String name, final ArgumentType<T> type) {
        return RequiredArgumentBuilder.argument(name, type);
    }

    /**
     * Helper method to create a new literal.
     * @param name The name of the literal. This is used to identify the literal.
     * @return a new {@link LiteralArgumentBuilder} instance.
     */
    protected static LiteralArgumentBuilder<CommandSource> literal(final String name) {
        return LiteralArgumentBuilder.literal(name);
    }

    /**
     * Registers the command and its aliases.
     * @param dispatcher The command dispatcher to register the command to.
     * @implNote This method is called by {@link Commands#add(Command)}.
     */
    public void register(CommandDispatcher<CommandSource> dispatcher) {
        register(dispatcher, name);

        for (String alias : aliases) {
            register(dispatcher, alias);
        }
    }

    private void register(CommandDispatcher<CommandSource> dispatcher, String name) {
        LiteralArgumentBuilder<CommandSource> builder = literal(name);
        build(builder);

        dispatcher.register(builder);
    }
}
