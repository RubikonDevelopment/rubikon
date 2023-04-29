package dev.inditium.repositories.factories;

import com.google.gson.JsonObject;
import dev.inditium.api.abstraction.Serializable;
import dev.inditium.api.feature.Feature;
import dev.inditium.api.repository.Repository;

public class FeatureFactory {
    private final String name;
    private final Feature feature;
    private String description;
    private final int key;

    public FeatureFactory(String name,Feature feature,int key,String description) {
        this.name = name;
        this.feature = feature;
        this.key = key;
        this.description = description;
    }


    public static FeatureBuilder builder() {
        return new FeatureBuilder();
    }

    public static class FeatureBuilder extends Repository<FeatureFactory,Integer> implements Serializable {
        private String name;
        private Feature feature;
        private String description = "No Description provided";
        private int key;

        public FeatureBuilder name(String name) {
            this.name = name;
            return this;
        }
        public FeatureBuilder feature(Feature feature) {
            this.feature = feature;
            return this;
        }
        public FeatureBuilder key(int key) {
            this.key = key;
            return this;
        }
        public FeatureBuilder description(String description) {
            this.description = description;
            return this;
        }

        public void build() {
            //reflection for settings and loading must be done here later
            FeatureFactory featureFactory = new FeatureFactory(name,feature,key,description);
            add(featureFactory,hashCode());
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
