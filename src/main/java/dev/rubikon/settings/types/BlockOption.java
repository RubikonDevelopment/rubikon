package dev.rubikon.settings.types;

import dev.rubikon.settings.Option;
import net.minecraft.block.Block;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;

import java.util.function.Predicate;

public class BlockOption extends Option<Block> {
    private final Predicate<Block> validator;

    public BlockOption(String name, String description, Block defaultValue) {
        this(name, description, defaultValue, null);
    }

    public BlockOption(String name, String description, Block defaultValue, Predicate<Block> validator) {
        super(name, description, defaultValue);

        this.validator = validator;
    }

    @Override
    protected NbtCompound save(NbtCompound nbt) {
        nbt.putInt("value", Registries.BLOCK.getRawId(get()));

        return nbt;
    }

    @Override
    protected Block load(NbtCompound nbt) {
        if (nbt.contains("value"))
            set(Registries.BLOCK.get(nbt.getInt("value")));

        return get();
    }

    @Override
    public boolean isValid(Block value) {
        return validator == null || validator.test(value);
    }
}
