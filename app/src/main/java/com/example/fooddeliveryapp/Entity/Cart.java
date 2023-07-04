package com.example.fooddeliveryapp.Entity;


import com.example.fooddeliveryapp.Helper.JsonHelper;

import java.util.List;


public class Cart {
    private String id;

    private String userId;

    private String listFood;

    public Cart( String id,String userId, String listFood) {
        this.id = id;
        this.userId = userId;
        this.listFood = listFood;
    }

    public Cart() {
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

    public double totalFee(){
        List<Food> list = JsonHelper.parseJsonToList(listFood, Food.class);
        double total = 0.0;
        for (int i = 0; i < list.size(); i++){
            total += list.get(i).getNumberInCart() * list.get(i).getFee();
        }
        return total;
    }
    public int cartSize(){
        List<Food> list = JsonHelper.parseJsonToList(listFood, Food.class);
        return list.size();
    }
}
