package com.codeinger.roomdb.db.database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.codeinger.roomdb.db.enitiy.Category;

@Entity(foreignKeys = {@ForeignKey(entity = Category.class,
        parentColumns = "id",
        childColumns = "categoryId",
        onDelete = ForeignKey.CASCADE)
})
public class Product {
    @PrimaryKey(autoGenerate = true)
    long id;
    String name;
    String image;
    long categoryId;

    public Product(String name, String image, long categoryId) {
        this.name = name;
        this.image = image;
        this.categoryId = categoryId;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
