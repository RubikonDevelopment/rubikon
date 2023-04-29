package dev.rubikon.repositories;

import dev.rubikon.features.Test;
import dev.rubikon.api.feature.impl.Feature;

public class FeatureRepository {

    public FeatureRepository() {
        Feature.builder()
                .name("Test")
                .feature(new Test())
                .key(0)
                .description("Developer feature for testing purposes")
                .build();
    }
}
