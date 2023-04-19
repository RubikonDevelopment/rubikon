package dev.inditium.api.serialization;

import com.google.gson.Gson;

public interface Serializable {
    default String serialize() {
        return new Gson().toJson(this);
    }
}
