package dev.rubikon.things.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.rubikon.things.Things;
import dev.rubikon.things.commands.Command;
import dev.rubikon.utils.ChatUtils;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

import static com.mojang.brigadier.Command.SINGLE_SUCCESS;

public class SaveCommand extends Command {
    public SaveCommand() {
        super("save", "saves the current settings", "save");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        builder.executes(context -> {
            Things.save();

            ChatUtils.sendMessage("Everything has been saved.");

            return SINGLE_SUCCESS;
        });
    }
}
