package com.example.fooddeliveryapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fooddeliveryapp.Dao.AppDatabase;
import com.example.fooddeliveryapp.Entity.Cart;
import com.example.fooddeliveryapp.Entity.Food;
import com.example.fooddeliveryapp.Helper.ManagementCart;
import com.example.fooddeliveryapp.R;

import java.util.ArrayList;
import java.util.List;

public class ShowDetailActivity extends AppCompatActivity {

    private TextView addToCartBtn;
    private TextView titleTxt,feeTxt,descriptionTxt,numberOrderTxt,starTxt,caloriesTxt,timeTxt,totalPriceTxt;
    private ImageView plusBtn,minusBtn,picFood;
    private int fid;
    private int numberOrder = 1;

    AppDatabase db;
    private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        db = AppDatabase.getInstance(getApplicationContext());
        managementCart = new ManagementCart(this);
        initView();
        getBundle();
    }

    private void getBundle() {
        fid = getIntent().getIntExtra("fid",0);
        new Thread(() -> {
            Food food = db.foodDao().findById(fid);
            runOnUiThread(() -> {
                int drawableResourceId = this.getResources().getIdentifier(food.getPic(), "drawable", this.getPackageName());
                Glide.with(this).load(drawableResourceId).into(picFood);
                titleTxt.setText(food.getTitle());
                feeTxt.setText("$" + food.getFee());
                descriptionTxt.setText(food.getDescription());
                starTxt.setText(food.getStar()+"");
                caloriesTxt.setText(food.getCalories()+" Calories");
                timeTxt.setText(food.getTime()+" minutes");
                numberOrderTxt.setText(numberOrder+"");
                totalPriceTxt.setText("$" + food.getFee() * numberOrder);

                plusBtn.setOnClickListener(v -> {
                    numberOrder++;
                    numberOrderTxt.setText(numberOrder+"");
                    totalPriceTxt.setText("$" + food.getFee() * numberOrder);
                });
                minusBtn.setOnClickListener(v -> {
                    if(numberOrder > 1){
                        numberOrder--;
                        numberOrderTxt.setText(numberOrder+"");
                        totalPriceTxt.setText("$" + food.getFee() * numberOrder);
                    }
                });

                addToCartBtn.setOnClickListener(v -> {
                    food.setNumberInCart(numberOrder);
                    managementCart.insertFood(food);
                });
            });
        }).start();

    }

    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.priceTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberItemTxt);
        plusBtn = findViewById(R.id.plusCartBtn);
        minusBtn = findViewById(R.id.minusCartBtn);
        picFood = findViewById(R.id.foodPic);
        starTxt = findViewById(R.id.starTxt);
        caloriesTxt = findViewById(R.id.starTxt);
        timeTxt = findViewById(R.id.timeTxt);
        totalPriceTxt = findViewById(R.id.totalPriceTxt);
    }

    private void AddToCart(Food food) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int id = Integer.parseInt(sharedPreferences.getString("user_id",""));
        new Thread(() -> {
            List<Food> foods ;
            Cart cart = db.cartDao().findByUserId(id);
            if (cart ==null) {

            }

        }).start();

    }
}