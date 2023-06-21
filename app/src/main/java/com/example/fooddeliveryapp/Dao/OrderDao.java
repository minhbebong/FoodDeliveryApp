package com.example.fooddeliveryapp.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.fooddeliveryapp.Entity.Order;

import java.util.List;

@Dao
public interface OrderDao {

    @Query("SELECT * FROM cart WHERE userId = :userId")
    List<Order> findByUserId(int userId);

    @Query("SELECT * FROM cart WHERE id = :id")
    Order findById(int id);

    @Insert
    void insertAll(Order... orders);

    @Insert
    void insert(Order order);
}
