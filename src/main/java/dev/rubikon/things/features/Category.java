package dev.rubikon.things.features;

import lombok.Getter;
import net.minecraft.item.Item;

public class Category {
    @Getter
    private final String name;
    @Getter
    private final Item icon;
    private final int hashCode;

    public Category(final String name) {
        this(name, null);
    }

    public Category(final String name, final Item icon) {
        this.name = name;
        this.icon = icon;
        this.hashCode = this.name.hashCode();
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Category && this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }
}
