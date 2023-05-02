package dev.rubikon.settings.types;

import dev.rubikon.settings.Option;
import dev.rubikon.utils.RegistriesUtils;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.function.Predicate;

/**
 * Represents an item option.
 * @see Option
 * @see Item
 */
public class ItemOption extends Option<Item> {
    private final Predicate<Item> validator;

    public ItemOption(String name, String description, Item defaultValue) {
        this(name, description, defaultValue, null);
    }

    public ItemOption(String name, String description, Item defaultValue, Predicate<Item> validator) {
        super(name, description, defaultValue);

        this.validator = validator;
    }

    @Override
    public boolean parse(String value) {
        return super.parse(RegistriesUtils.findByString(Registries.ITEM, value));
    }

    @Override
    public Iterable<Identifier> commandIdentifierSuggestions() {
        return Registries.ITEM.getIds();
    }

    @Override
    protected NbtCompound save(NbtCompound nbt) {
        nbt.putInt("value", Registries.ITEM.getRawId(get()));

        return nbt;
    }

    @Override
    protected Item load(NbtCompound nbt) {
        if (nbt.contains("value"))
            set(Registries.ITEM.get(nbt.getInt("value")));

        return get();
    }

    @Override
    public boolean isValid(Item value) {
        return validator == null || validator.test(value);
    }
}
