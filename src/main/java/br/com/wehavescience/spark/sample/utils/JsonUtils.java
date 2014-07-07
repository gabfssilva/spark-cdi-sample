package br.com.wehavescience.spark.sample.utils;

import com.google.gson.Gson;

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
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
