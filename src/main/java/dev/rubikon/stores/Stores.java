package dev.rubikon.stores;

import lombok.experimental.UtilityClass;

/**
 * Used for storing {@link FeatureStore features} & {@link ResourceLoadingStore resources used for rendering}.
 * @see FeatureStore
 * @see ResourceLoadingStore
 */
@UtilityClass
public class Stores {
    public static FeatureStore FEATURE = new FeatureStore();
    public static ResourceLoadingStore RESOURCE = new ResourceLoadingStore();


    public static void init() {
        RESOURCE.init();
        FEATURE.init();
    }
}
