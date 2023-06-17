package com.example.fooddeliveryapp.Helper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonHelper {
    public static Gson gson = new Gson();

    public static <T> String parseListToJson(List<T> list) {
        return gson.toJson(list);
    }

    public static <T> List<T> parseJsonToList(String json, Class<T> clazz) {
        Type listType = TypeToken.getParameterized(List.class, clazz).getType();
        return gson.fromJson(json, listType);
    }
}
