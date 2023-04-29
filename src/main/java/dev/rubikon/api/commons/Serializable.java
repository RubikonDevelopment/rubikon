package dev.rubikon.api.commons;

import com.google.gson.JsonObject;

/**
 * used for serializing client only features and saving them into jsons
 */
public interface Serializable {

    JsonObject save();
    void load(JsonObject jsonObject);
}
