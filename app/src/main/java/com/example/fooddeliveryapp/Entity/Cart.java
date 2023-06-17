package com.example.fooddeliveryapp.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.fooddeliveryapp.Helper.JsonHelper;

import java.util.List;


@Entity
public class Cart {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    public int userId;

    @ColumnInfo
    public String listFood;

    public Cart( int userId, String listFood) {
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

    public double getTotalFee(){
        List<Food> list = JsonHelper.parseJsonToList(listFood, Food.class);
        double total = 0.0;
        for (int i = 0; i < list.size(); i++){
            total += list.get(i).getNumberInCart() * list.get(i).getFee();
        }
        return total;
    }
    public int getCartSize(){
        List<Food> list = JsonHelper.parseJsonToList(listFood, Food.class);
        return list.size();
    }
}
