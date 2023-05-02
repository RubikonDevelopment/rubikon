package dev.rubikon.things.features.misc;

import dev.rubikon.Rubikon;
import dev.rubikon.settings.Option;
import dev.rubikon.settings.OptionGroup;
import dev.rubikon.settings.types.BoolOption;
import dev.rubikon.settings.types.IntOption;
import dev.rubikon.settings.types.StringListOption;
import dev.rubikon.things.features.Feature;
import dev.rubikon.utils.ListUtils;
import dev.rubikon.utils.ChatUtils;

import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_K;

public class Test extends Feature {
    private final OptionGroup generalGroup = getOptions().defaultGroup();

    private final Option<Boolean> sayRandomWord = generalGroup.add(new BoolOption(
            "say-random-word",
            "Says random word when enabled",
            true
            )
    );

    private final Option<Integer> randomWordDelay = generalGroup.add(new IntOption(
            "random-word-delay",
            "Delay between saying random words",
            1000,
            0,
            10000
            )
    );

    private final Option<List<String>> randomWords = generalGroup.add(new StringListOption(
            "random-words",
            "List of random words",
            ListUtils.toArrayList("test1", "test2", "test3")
            )
    );

    public Test() {
        super("Test", "Developer feature for testing purposes", GLFW_KEY_K);
    }

    @Override
    public void onEnable() {
        Rubikon.LOGGER.info("on");

        if (sayRandomWord.get()) {
            String randomWord = randomWords.get().get((int) (Math.random() * randomWords.get().size()));
            ChatUtils.sendMessage(randomWord);
        }

        ChatUtils.sendMessage("Random Word Delay: " + randomWordDelay.get());
    }

    @Override
    public void onDisable() {
        Rubikon.LOGGER.info("off");
    }
}
