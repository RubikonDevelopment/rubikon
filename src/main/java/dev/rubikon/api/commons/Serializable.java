package dev.rubikon.api.commons;

import com.google.gson.JsonObject;

/**
 * This interface is used to save and load data from the config(json) file.
 */
public interface Serializable {

    JsonObject save();
    void load(JsonObject jsonObject);
}
