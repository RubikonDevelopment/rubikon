package dev.rubikon.stores;

import lombok.experimental.UtilityClass;

/**
 * Used for storing {@link FeatureStore features} & {@link TextRendererStore fonts used for rendering}.
 * @see FeatureStore
 * @see TextRendererStore
 */
@UtilityClass
public class Stores {
    public static FeatureStore FEATURE = new FeatureStore();
    public static TextRendererStore TEXT = new TextRendererStore();

    public static void init() {
        FEATURE.init();
    }

    public static void initRenderer() {
        TEXT.init();
    }
}
