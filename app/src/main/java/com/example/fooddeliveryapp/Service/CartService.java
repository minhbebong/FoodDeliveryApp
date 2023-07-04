package com.example.fooddeliveryapp.Service;

import static com.example.fooddeliveryapp.Helper.UserHelper.getCurrentUserId;

import android.content.Context;
import android.widget.Toast;

import com.example.fooddeliveryapp.Activity.ShowDetailActivity;
import com.example.fooddeliveryapp.Dao.CartDao;
import com.example.fooddeliveryapp.Entity.Cart;
import com.example.fooddeliveryapp.Entity.Food;
import com.example.fooddeliveryapp.Helper.JsonHelper;
import com.example.fooddeliveryapp.Helper.UserHelper;
import com.example.fooddeliveryapp.Interface.MyCallBack;
import com.google.firebase.database.DatabaseError;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CartService {
    public static void AddToCart(Food food, Context context) {
        CartDao.getInstance().getUserCart(getCurrentUserId(context), new MyCallBack<Cart>() {
            @Override
            public void onLoaded(Cart cart) {
                String uid = getCurrentUserId(context);
                List<Food> foods ;
                if (cart ==null) {
                    String listFoodJson = JsonHelper.parseListToJson(Collections.singletonList(food));
                    Cart newCart = new Cart(UUID.randomUUID().toString(),uid,listFoodJson);
                    CartDao.getInstance().save(newCart);
                }else {
                    foods = JsonHelper.parseJsonToList(cart.getListFood(),Food.class);
                    boolean flag = false;
                    for(Food f : foods) {
                        if(f.getId().equals(food.getId())) {
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
                    CartDao.getInstance().save(cart);
                }
                Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(context, "Server Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
