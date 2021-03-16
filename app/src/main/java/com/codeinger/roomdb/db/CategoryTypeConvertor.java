package com.codeinger.roomdb.db;

import androidx.room.TypeConverter;

import com.codeinger.roomdb.db.enitiy.Category;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CategoryTypeConvertor{
    @TypeConverter
    public Category.Type getCategoryType(String data){
        Gson gson = new Gson();
        if(data==null){
            return Category.Type.GAMES;
        }

        java.lang.reflect.Type listType = new TypeToken<Category.Type>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public String setCategoryType(Category.Type type){
        return new Gson().toJson(type);
    }


}

