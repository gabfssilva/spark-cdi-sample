package br.com.wehavescience.spark.sample.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
public class JsonUtils {
    private JsonUtils(){
    }

    public static <T> T asObject(String json, Class<T> clazz){
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

    public static <T> String asJson(T object){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);
    }
}
