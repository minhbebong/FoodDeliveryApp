package com.example.fooddeliveryapp.Interface;

import com.example.fooddeliveryapp.Entity.Food;

@FunctionalInterface
public interface CartChange {
    void execute(Food food);
}
