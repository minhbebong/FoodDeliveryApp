package com.example.fooddeliveryapp.Service;

import static com.example.fooddeliveryapp.Helper.UserHelper.getCurrentUserId;

import android.content.Context;

import com.example.fooddeliveryapp.Dao.AppDatabase;
import com.example.fooddeliveryapp.Entity.Cart;
import com.example.fooddeliveryapp.Entity.Food;
import com.example.fooddeliveryapp.Helper.JsonHelper;

import java.util.Collections;
import java.util.List;

public class CartService {

    public static void AddToCart(Food food, AppDatabase db, Context context) {
        int uid = getCurrentUserId(context);
        new Thread(() -> {
            List<Food> foods ;
            Cart cart = db.cartDao().findByUserId(uid);
            if (cart ==null) {
                String listFoodJson = JsonHelper.parseListToJson(Collections.singletonList(food));
                Cart newCart = new Cart(uid,listFoodJson);
                db.cartDao().insert(newCart);
            }else {
                foods = JsonHelper.parseJsonToList(cart.getListFood(),Food.class);
                boolean flag = false;
                for(Food f : foods) {
                    if(f.getId() == food.getId()) {
                        f.setNumberInCart(f.getNumberInCart() + food.getNumberInCart());
                        flag = true;
                        break;
                    }
                }
                if(!flag) {
                    foods.add(food);
                }
                String listFoodJson = JsonHelper.parseListToJson(foods);
                cart.setListFood(listFoodJson);
                db.cartDao().update(cart);
            }
        }).start();
    }

    public static Cart UpdateInCart(Food food, AppDatabase db, Context context) {
        int uid = getCurrentUserId(context);
        List<Food> foods ;
        Cart cart = db.cartDao().findByUserId(uid);
        if (cart !=null) {
            foods = JsonHelper.parseJsonToList(cart.getListFood(),Food.class);
            for(Food f : foods) {
                if(f.getId() == food.getId()) {
                    if(food.getNumberInCart() == 0) {
                        foods.remove(f);
                    }else {
                        f.setNumberInCart(food.getNumberInCart());
                    }
                    break;
                }
            }
            String listFoodJson = JsonHelper.parseListToJson(foods);
            cart.setListFood(listFoodJson);
            db.cartDao().update(cart);
        }
        return cart;
    }
}
