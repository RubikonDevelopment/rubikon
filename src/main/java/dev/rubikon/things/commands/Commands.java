package dev.rubikon.things.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.rubikon.commons.Store;
import dev.rubikon.things.Thing;
import dev.rubikon.things.Things;
import dev.rubikon.things.commands.commands.*;
import dev.rubikon.things.features.Features;
import lombok.Getter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.nbt.NbtCompound;

import java.util.Collection;
import java.util.HashMap;

public class Commands extends Thing<Commands> implements Store<String, Command> {
    @Getter
    private final HashMap<String, Command> commands = new HashMap<>(); // HashMap for faster finding
    public static final CommandDispatcher<CommandSource> DISPATCHER = new CommandDispatcher<>();
    public static final CommandSource COMMAND_SOURCE = new ClientCommandSource(null, MinecraftClient.getInstance());

    public Commands() {
        super("commands");
    }

    @Override
    public void init() {
        add(new TestCommand());
        add(new ToggleCommand());
    }

    public static Commands get() {
        return Things.get(Commands.class);
    }

    public void add(Command command) {
        commands.put(command.getName(), command);
        command.register(DISPATCHER);
    }

    public Command find(String name) {
        return commands.get(name);
    }

    public Collection<String> names() {
        return commands.keySet();
    }

    public Collection<Command> all() {
        return commands.values();
    }

    public void dispatch(String message) throws CommandSyntaxException {
        DISPATCHER.execute(message, COMMAND_SOURCE);
    }

    @Override
    public NbtCompound toNbtTag() {
        return null;
    }

    @Override
    public Commands fromNbtTag(NbtCompound nbt) {
        return this;
    }
}
