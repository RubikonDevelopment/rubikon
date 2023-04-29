package dev.inditium.repositories;

import dev.inditium.features.Test;
import dev.inditium.repositories.factories.Feature;

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
