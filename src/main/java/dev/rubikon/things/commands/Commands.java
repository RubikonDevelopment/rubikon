package dev.rubikon.things.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.rubikon.utils.Store;
import dev.rubikon.things.Thing;
import dev.rubikon.things.Things;
import dev.rubikon.things.commands.commands.*;
import lombok.Getter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.nbt.NbtCompound;

import java.util.Collection;
import java.util.HashMap;

/**
 * Represents the commands' thing.
 * This thing is responsible for registering and managing all commands.
 * <p>
 *     All commands are stored in a HashMap for faster finding.
 *     The commands are registered using the CommandDispatcher.
 *     The {@link CommandDispatcher} is used to dispatch commands.
 *     The {@link CommandSource} is used as the command source.
 *     <br>
 *     <br>
 *     To register a command, you need to call the {@link #add(Command)} method.
 *     <br>
 *     <br>
 *     This is singleton class, so you can access it using the {@link #get()} method.
 * </p>
 *
 * @see Command
 * @see Store
 * @see Thing
 */
public class Commands extends Thing<Commands> implements Store<String, Command> {
    @Getter
    private final HashMap<String, Command> commands = new HashMap<>(); // HashMap for faster finding
    public static final CommandDispatcher<CommandSource> DISPATCHER = new CommandDispatcher<>();
    public static final CommandSource COMMAND_SOURCE = new ClientCommandSource(null, MinecraftClient.getInstance());

    public Commands() {
        super("commands");
    }

    /**
     * Initializes all commands.
     * <p>
     *     This method is called in the {@link Things#add(Thing)} method.
     * </p>
     *
     * @see Things#init()
     * @see Things#add(Thing)
     */
    @Override
    public void init() {
        add(new OptionsCommand());
        add(new SaveCommand());
        add(new TestCommand());
        add(new ToggleCommand());
    }

    /**
     * @return The commands' thing instance.
     */
    public static Commands get() {
        return Things.get(Commands.class);
    }

    /**
     * Registers a command.
     * <p>
     *     This method is used to register a command.
     *     <br>
     *     <br>
     *     Before registering the command, it is stored in the {@link #commands} HashMap.
     *     It uses {@link CommandDispatcher} to register the command.
     *     <br>
     *     <br>
     *     This method is called in the {@link #init()} method.
     * </p>
     * @param command The command to register.
     *
     * @see Command
     * @see CommandDispatcher
     */
    public void add(Command command) {
        commands.put(command.getName(), command);
        command.register(DISPATCHER);
    }

    /**
     * Finds a command by its name.
     * @param name The name of the command.
     * @return The command if found, `null` otherwise.
     */
    public Command find(String name) {
        return commands.get(name);
    }

    /**
     * @return A collection of all command names.
     */
    public Collection<String> names() {
        return commands.keySet();
    }

    /**
     * @return A collection of all commands.
     */
    public Collection<Command> all() {
        return commands.values();
    }

    /**
     * Dispatches a command.
     * @param message The command message.
     * @throws CommandSyntaxException if the command syntax is invalid.
     *
     * @implNote This method is called by {@link dev.rubikon.mixin.ClientPlayNetworkHandlerMixin} and {@link dev.rubikon.mixin.ScreenMixin}
     */
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
