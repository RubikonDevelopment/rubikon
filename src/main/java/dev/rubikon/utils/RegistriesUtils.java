package dev.rubikon.utils;

import lombok.experimental.UtilityClass;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * Represents a utility class for registry-related operations.
 */
@UtilityClass
public class RegistriesUtils {
    /**
     * Finds an object in a registry by its string name.
     * @param registry The registry to search in.
     * @param name The name of the object to find.
     * @return The found object if found, `null` otherwise.
     */
    public static <T> T findByString(Registry<T> registry, String name) {
        name = name.trim().toLowerCase();

        if (name.isEmpty()) return null;

        Identifier identifier = name.contains(":") ? new Identifier(name) : new Identifier("minecraft", name);

        if (registry.containsId(identifier))
            return registry.get(identifier);

        return null;
    }
}
