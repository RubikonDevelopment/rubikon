package dev.rubikon.stores;

import dev.rubikon.api.commons.Store;
import dev.rubikon.api.feature.Feature;
import dev.rubikon.features.Logo;
import dev.rubikon.features.Sprint;
import dev.rubikon.features.Test;
import lombok.extern.java.Log;


/**
 * Used for storing features.
 * @see #init()
 */
public class FeatureStore extends Store<String, Feature> {

    /**
     * Add features to the store here.
     */
    public void init() {
        add("test", new Test());
        add("sprint",new Sprint());
        add("logo",new Logo());
    }
}
