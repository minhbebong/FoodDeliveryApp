package com.example.fooddeliveryapp.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.fooddeliveryapp.Entity.Category;
import com.example.fooddeliveryapp.Entity.Food;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM category")
    List<Category> getAll();

    @Insert
    void insertAll(Category... categories);
}
