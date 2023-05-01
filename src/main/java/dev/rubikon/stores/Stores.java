package dev.rubikon.stores;

import lombok.experimental.UtilityClass;

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
