package dev.inditium.api.abstraction;

import com.google.gson.JsonObject;

public interface Serializable {

    JsonObject save();
    void load(JsonObject jsonObject);
}
