package dev.rubikon.repositories;

import dev.rubikon.features.Test;
import dev.rubikon.api.feature.impl.Feature;

import static org.lwjgl.glfw.GLFW.*;

public class FeatureRepository {

    public FeatureRepository() {}

    public void init() {
        Feature.builder()
                .name("Test")
                .feature(new Test())
                .key(GLFW_KEY_K)
                .description("Developer feature for testing purposes")
                .build();
    }
}
