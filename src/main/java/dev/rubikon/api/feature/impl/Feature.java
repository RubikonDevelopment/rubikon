package dev.rubikon.api.feature.impl;

import com.google.gson.JsonObject;
import dev.rubikon.Rubikon;
import dev.rubikon.api.commons.Serializable;
import dev.rubikon.api.feature.AstractFeature;
import dev.rubikon.api.commons.Repository;
import dev.rubikon.events.KeyPressEvent;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class Feature {
    private final String name;
    private final AstractFeature astractFeature;
    private String description;
    private final int key;

    public Feature(String name, AstractFeature astractFeature, int key, String description) {
        this.name = name;
        this.astractFeature = astractFeature;
        this.key = key;
        this.description = description;
    }


    public static FeatureBuilder builder() {
        return new FeatureBuilder();
    }

    public static class FeatureBuilder extends Repository<Feature,Integer> implements Serializable {
        private String name;
        private AstractFeature astractFeature;
        private String description = "No Description provided";
        private int key;

        public FeatureBuilder name(String name) {
            this.name = name;
            return this;
        }
        public FeatureBuilder feature(AstractFeature astractFeature) {
            this.astractFeature = astractFeature;
            return this;
        }
        public FeatureBuilder key(int key) {
            //should be a GLFW class key
            this.key = key;
            return this;
        }
        public FeatureBuilder description(String description) {
            this.description = description;
            return this;
        }

        public void build() {
            //reflection for settings and loading must be done here later
            //key toggle callback
            Rubikon.getEventPubSub().subscribe(KeyPressEvent.class,event -> {
                if (event.getKey() == this.key && event.getAction() == GLFW_PRESS) {
                    astractFeature.toggle();
                }
            });
            add(new Feature(name, astractFeature,key,description),astractFeature.hashCode());
        }

        @Override
        public JsonObject save() {
            return null;
        }

        @Override
        public void load(JsonObject jsonObject) {

        }
    }
}
