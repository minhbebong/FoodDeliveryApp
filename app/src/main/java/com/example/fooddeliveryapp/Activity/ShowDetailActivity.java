package com.example.fooddeliveryapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fooddeliveryapp.Dao.FoodDao;
import com.example.fooddeliveryapp.Entity.Food;
import com.example.fooddeliveryapp.Interface.MyCallBack;
import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.Service.CartService;
import com.google.firebase.database.DatabaseError;


public class ShowDetailActivity extends AppCompatActivity {

    private TextView addToCartBtn;
    private TextView titleTxt,feeTxt,descriptionTxt,numberOrderTxt,starTxt,caloriesTxt,timeTxt,totalPriceTxt;
    private ImageView plusBtn,minusBtn,picFood;
    private Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        initView();
        getBundle();
    }

    private void getBundle() {
        String fid = getIntent().getStringExtra("fid");

        FoodDao.getInstance().findById(fid, new MyCallBack<Food>() {
            @Override
            public void onLoaded(Food food) {
                food.setNumberInCart(1); // set default number in cart
                int drawableResourceId = ShowDetailActivity.this.getResources().getIdentifier(food.getPic(), "drawable", ShowDetailActivity.this.getPackageName());
                Glide.with(ShowDetailActivity.this).load(drawableResourceId).into(picFood);
                titleTxt.setText(food.getTitle());
                feeTxt.setText("$" + food.getFee());
                descriptionTxt.setText(food.getDescription());
                starTxt.setText(food.getStar()+"");
                caloriesTxt.setText(food.getCalories()+" Calories");
                timeTxt.setText(food.getTime()+" minutes");
                numberOrderTxt.setText(food.getNumberInCart()+"");
                totalPriceTxt.setText("$" + food.getFee() * food.getNumberInCart());

                plusBtn.setOnClickListener(v -> {
                    food.setNumberInCart(food.getNumberInCart()+1);
                    numberOrderTxt.setText(food.getNumberInCart()+"");
                    totalPriceTxt.setText("$" + food.getFee() * food.getNumberInCart());
                });

                minusBtn.setOnClickListener(v -> {
                    if(food.getNumberInCart() > 1){
                        food.setNumberInCart(food.getNumberInCart()-1);
                        numberOrderTxt.setText(food.getNumberInCart()+"");
                        totalPriceTxt.setText("$" + food.getFee() * food.getNumberInCart());
                    }
                });
                addToCartBtn.setOnClickListener(v -> {
                    CartService.AddToCart(food,ShowDetailActivity.this);
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
        caloriesTxt = findViewById(R.id.caloriesTxt);
        timeTxt = findViewById(R.id.timeTxt);
        totalPriceTxt = findViewById(R.id.totalPriceTxt);
    }


}