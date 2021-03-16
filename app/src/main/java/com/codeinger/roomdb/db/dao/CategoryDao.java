package com.codeinger.roomdb.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.codeinger.roomdb.db.enitiy.Category;

import java.util.List;

@Dao
public interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insetCategory(Category category);

    @Query("SELECT * FROM Category WHERE type = (:type)")
    List<Category> getCAtegoryList(Category.Type type);
}
