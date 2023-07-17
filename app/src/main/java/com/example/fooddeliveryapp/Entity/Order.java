package com.example.fooddeliveryapp.Entity;


import com.example.fooddeliveryapp.Helper.JsonHelper;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private String id;

    private String userId;

    private String date;

    private String listFood;

    public Order() {
    }

    public Order(String id,String userId, String listFood) {
        this.id = id;
        this.userId = userId;
        this.listFood = listFood;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getListFood() {
        return listFood;
    }

    public void setListFood(String listFood) {
        this.listFood = listFood;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public double totalFee(){
        List<Food> list = JsonHelper.parseJsonToList(listFood, Food.class);
        double total = 0.0;
        for (int i = 0; i < list.size(); i++){
            total += list.get(i).getNumberInCart() * list.get(i).getFee();
        }
        return total;
    }
}
