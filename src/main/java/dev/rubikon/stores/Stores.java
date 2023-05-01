package dev.rubikon.stores;

import lombok.experimental.UtilityClass;

/**
 * Used for storing and initializing stores.
 * @see FeatureStore
 * @see ResourceLoadingStore
 * @see CommandStore
 */
@UtilityClass
public class Stores {
    public static FeatureStore FEATURE = new FeatureStore();
    public static ResourceLoadingStore RESOURCE = new ResourceLoadingStore();
    public static CommandStore COMMAND = new CommandStore();


    public static void init() {
        RESOURCE.init();
        FEATURE.init();
        COMMAND.init();
    }
}