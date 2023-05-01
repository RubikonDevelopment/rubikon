package dev.rubikon.api.commands;

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
 * The base class for all commands.
 * All commands have to extend this class.
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

    public abstract void build(LiteralArgumentBuilder<CommandSource> builder);

    protected static <T> RequiredArgumentBuilder<CommandSource, T> argument(final String name, final ArgumentType<T> type) {
        return RequiredArgumentBuilder.argument(name, type);
    }

    protected static LiteralArgumentBuilder<CommandSource> literal(final String name) {
        return LiteralArgumentBuilder.literal(name);
    }

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
