package com.example.fooddeliveryapp.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.fooddeliveryapp.Entity.Cart;
import com.example.fooddeliveryapp.Entity.Food;

import java.util.List;

@Dao
public interface FoodDao {

    //find 5 food have the highest rating
    @Query("SELECT * FROM food ORDER BY star DESC LIMIT 5")
    List<Food> findTop5Food();

    //find by id
    @Query("SELECT * FROM food WHERE id = :id")
    Food findById(int id);

    //find by title
    @Query("SELECT * FROM food WHERE title = :title")
    List<Food> findByTitle(String title);

    //find by category
    @Query("SELECT * FROM food WHERE category = :category")
    List<Food> findByCategory(String category);

    @Insert
    void insertAll(Food... foods);
}
