package dev.rubikon.stores;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.rubikon.api.commands.Command;
import dev.rubikon.api.stores.Store;
import dev.rubikon.commands.commands.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommandSource;
import net.minecraft.command.CommandSource;

/**
 * Used for storing, registering and dispatching commands.
 * @see #init()
 * @see #dispatch(String)
 * @see #register(Command)
 * @see Command#register(CommandDispatcher)
 */
public class CommandStore extends Store<String, Command> {
    public static final CommandDispatcher<CommandSource> DISPATCHER = new CommandDispatcher<>();
    public static final CommandSource COMMAND_SOURCE = new ClientCommandSource(null, MinecraftClient.getInstance());

    public void init() {
        register(new TestCommand());
        register(new ToggleCommand());
    }

    public void register(Command command) {
        command.register(DISPATCHER);
        add(command.getName(), command);
    }

    public void dispatch(String message) throws CommandSyntaxException {
        DISPATCHER.execute(message, COMMAND_SOURCE);
    }
}
