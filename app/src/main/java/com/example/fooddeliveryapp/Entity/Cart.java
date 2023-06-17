package com.example.fooddeliveryapp.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Cart {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    public int userId;

    @ColumnInfo
    public String listFood;

    public Cart(int id, int userId, String listFood) {
        this.id = id;
        this.userId = userId;
        this.listFood = listFood;
    }

    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getListFood() {
        return listFood;
    }

    public void setListFood(String listFood) {
        this.listFood = listFood;
    }
}
