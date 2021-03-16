package com.codeinger.roomdb.db.database;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.codeinger.roomdb.db.CategoryTypeConvertor;
import com.codeinger.roomdb.db.dao.CategoryDao;
import com.codeinger.roomdb.db.dao.ProductDao;
import com.codeinger.roomdb.db.enitiy.Category;


@Database(entities = {Category.class,Product.class},version = 1,exportSchema = false)
@TypeConverters({CategoryTypeConvertor.class})
public abstract class MyDatabase extends RoomDatabase {

    static MyDatabase database;

    public static MyDatabase getDatabase(Context context) {
        if(database==null){
            database = Room.databaseBuilder(context.getApplicationContext(),MyDatabase.class,"Amir").allowMainThreadQueries().build();
        }
        return database;
    }

    public abstract CategoryDao getCategoryDao();
    public abstract ProductDao getProductDao();


}
