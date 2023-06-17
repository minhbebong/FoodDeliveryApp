package com.example.fooddeliveryapp.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fooddeliveryapp.Entity.Cart;

@Dao
public interface CartDao {
     @Query("SELECT * FROM cart WHERE userId = :userId")
     Cart findByUserId(int userId);

     // add multiple methods here
     @Insert
     void insertAll(Cart... carts);

     @Insert
     void insert(Cart cart);

     @Update
     void update(Cart cart);
}
