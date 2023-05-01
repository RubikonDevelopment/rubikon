package dev.rubikon.stores;

import dev.rubikon.api.stores.Store;
import dev.rubikon.api.feature.Feature;
import dev.rubikon.features.Logo;
import dev.rubikon.features.Sprint;
import dev.rubikon.features.Test;


public class FeatureStore extends Store<String, Feature> {

    public void init() {
        add("test", new Test());
        add("sprint",new Sprint());
        add("logo",new Logo());
    }
}
