package com.example.fooddeliveryapp.Entity;



public class Order {
    private String id;

    private String userId;

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
}
