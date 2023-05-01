package dev.rubikon.things.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.rubikon.settings.Option;
import dev.rubikon.things.commands.Command;
import dev.rubikon.things.commands.arguments.FeatureArgumentType;
import dev.rubikon.things.commands.arguments.OptionArgumentType;
import dev.rubikon.things.commands.arguments.OptionValueArgumentType;
import dev.rubikon.things.features.Feature;
import dev.rubikon.utils.ChatUtils;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

import static com.mojang.brigadier.Command.SINGLE_SUCCESS;

public class OptionsCommand extends Command {
    public OptionsCommand() {
        super("options", "Shows and manages options");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        builder.then(
                argument("feature", FeatureArgumentType.create())
                .then(
                        argument("option", OptionArgumentType.create())
                        .executes(context -> {
                            Feature feature = context.getArgument("feature", Feature.class);
                            Option<?> option = OptionArgumentType.get(context);

                            ChatUtils.sendMessage(
                                    Text.of(feature.getName()),
                                    "Option <highlight>%s<white> is <highlight>%s<white>.",
                                    option.getName(),
                                    option.get()
                            );

                            return SINGLE_SUCCESS;
                        })
                         .then(
                                 argument("value", OptionValueArgumentType.create())
                                  .executes(context -> {
                                      Feature feature = context.getArgument("feature", Feature.class);
                                      Option<?> option = OptionArgumentType.get(context);
                                      String value = context.getArgument("value", String.class);

                                      // TODO: set option value using `parse` method

                                      ChatUtils.sendMessage(
                                              Text.of(feature.getName()),
                                              "Option <highlight>%s<white> set to <highlight>%s<white>.",
                                              option.getName(),
                                              value
                                      );

                                      return SINGLE_SUCCESS;
                                  })
                         )
                )
        );
    }
}
