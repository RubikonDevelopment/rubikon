package dev.rubikon.stores;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Stores {
    public static FeatureStore FEATURE = new FeatureStore();
    public static ResourceLoadingStore RESOURCE = new ResourceLoadingStore();


    public static void init() {
        RESOURCE.init();
        FEATURE.init();
    }
}
