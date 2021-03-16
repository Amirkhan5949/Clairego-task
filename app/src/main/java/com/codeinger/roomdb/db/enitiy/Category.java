package com.codeinger.roomdb.db.enitiy;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

@Entity
public class Category {
    @PrimaryKey(autoGenerate = true)
    long id;
    String name;
    Type type;

    public Category(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type{
         GAMES,APPS,MOVIES,BOOKS
    }



}
