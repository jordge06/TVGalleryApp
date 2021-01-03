package com.example.tvapp.utilities;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

public class Converter {

    public static String[] fromString(String value) {
        Type list = new TypeToken<String[]>() {}.getType();
        return new Gson().fromJson(value, list);
    }

    @TypeConverter
    public static String fromStringArray(String[] value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }
}
