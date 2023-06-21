package com.example.fooddeliveryapp.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.fooddeliveryapp.Entity.Cart;
import com.example.fooddeliveryapp.Entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user WHERE email = :email")
    User findByEmail(String email);

    @Insert
    void addUser(User user);

    @Insert
    void insertAll(User... users);

    @Query("SELECT * FROM user Where id = :id")
    User findById(int id);
}
