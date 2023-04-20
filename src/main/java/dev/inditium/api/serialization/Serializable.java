package dev.inditium.api.serialization;

import com.google.gson.Gson;

public interface Serializable<T> {
    default String serialize() {
        return new Gson().toJson(this);
    }

    default T deserialize(String json) {
        return (T) new Gson().fromJson(json, this.getClass());
    }

    void save();
    void load();
}
