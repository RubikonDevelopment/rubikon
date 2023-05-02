package dev.rubikon.utils;

import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class RegistriesUtils {
    public static <T> T findByString(Registry<T> registry, String name) {
        name = name.trim().toLowerCase();

        if (name.isEmpty()) return null;

        Identifier identifier = name.contains(":") ? new Identifier(name) : new Identifier("minecraft", name);

        if (registry.containsId(identifier))
            return registry.get(identifier);

        return null;
    }
}
