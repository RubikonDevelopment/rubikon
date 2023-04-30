package dev.rubikon.stores;

import dev.rubikon.api.commons.Store;
import dev.rubikon.api.feature.Feature;
import dev.rubikon.features.Test;

public class FeatureStore extends Store<String, Feature> {

    public void init() {
        add("test", new Test());
    }
}
