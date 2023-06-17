package com.example.fooddeliveryapp.Activity;

import static com.example.fooddeliveryapp.constant.GlobalConstant.DELIVERY_FEE;
import static com.example.fooddeliveryapp.constant.GlobalConstant.PERCENT_TAX;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.fooddeliveryapp.Adapter.CartListAdapter;
import com.example.fooddeliveryapp.Dao.AppDatabase;
import com.example.fooddeliveryapp.Entity.Cart;
import com.example.fooddeliveryapp.Entity.Food;
import com.example.fooddeliveryapp.Helper.JsonHelper;
import com.example.fooddeliveryapp.Helper.UserHelper;
import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.Service.CartService;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerViewList;
    private TextView totalFeeTxt,taxTxt,deliveryFeeTxt,totalTxt,emptyTxt;
    private double tax,total,itemTotal;
    private ScrollView scrollView;

    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        db = AppDatabase.getInstance(getApplicationContext());

        initView();
        initList();
        bottomNavigation();
    }

    private void bottomNavigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);

        homeBtn.setOnClickListener(v -> {
            startActivity(new Intent(CartActivity.this,MainActivity.class));
        });

        cartBtn.setOnClickListener(v -> {
            startActivity(new Intent(CartActivity.this,CartActivity.class));
        });
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);

        new Thread(() -> {
            Cart cart = db.cartDao().findByUserId(UserHelper.getCurrentUserId(this));
            runOnUiThread(() -> {
                if (cart == null || cart.getCartSize() == 0) {
                    emptyTxt.setVisibility(LinearLayout.VISIBLE);
                    scrollView.setVisibility(LinearLayout.GONE);
                }else {
                    List<Food> listFood = JsonHelper.parseJsonToList(cart.getListFood(), Food.class);
                    RecyclerView.Adapter adapter = new CartListAdapter(listFood, changedFood -> {
                        new Thread(() -> {
                            Cart newCart = CartService.UpdateInCart(changedFood,db,this);
                            runOnUiThread(() -> {
                                if (newCart.getCartSize() == 0) {
                                    emptyTxt.setVisibility(LinearLayout.VISIBLE);
                                    scrollView.setVisibility(LinearLayout.GONE);
                                }
                                calculateCart(newCart);
                            });
                        }).start();
                    });
                    calculateCart(cart);
                    recyclerViewList.setAdapter(adapter);

                    emptyTxt.setVisibility(LinearLayout.GONE);
                    scrollView.setVisibility(LinearLayout.VISIBLE);
                }
            });


        }).start();


    }
    private void calculateCart(Cart cart) {
        if(cart == null) return;
        double totalFee = cart.getTotalFee();
        tax = (double)Math.round(totalFee*PERCENT_TAX *100)/100;
        itemTotal = (double)Math.round(totalFee*100)/100;
        total = (double)Math.round((itemTotal + tax + DELIVERY_FEE)*100)/100;

        totalFeeTxt.setText("$"+itemTotal);
        taxTxt.setText("$"+tax);
        deliveryFeeTxt.setText("$"+DELIVERY_FEE);
        totalTxt.setText("$"+total);

    }

    private void initView() {
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryFeeTxt = findViewById(R.id.deliveryFeeTxt);
        totalTxt = findViewById(R.id.totalTxt);
        recyclerViewList = findViewById(R.id.view);
        scrollView = findViewById(R.id.scrollView);
        emptyTxt = findViewById(R.id.emptyTxt);
    }
}