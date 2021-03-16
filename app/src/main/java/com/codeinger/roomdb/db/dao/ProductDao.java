package com.codeinger.roomdb.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.codeinger.roomdb.db.database.Product;
import com.codeinger.roomdb.db.enitiy.Category;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insetProduct(Product category);

    @Query("SELECT * FROM Product WHERE categoryId = (:id)")
    List<Product> getProductList(long id);

}
