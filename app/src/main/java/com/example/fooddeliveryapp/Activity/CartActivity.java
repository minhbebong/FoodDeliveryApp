package com.example.fooddeliveryapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.fooddeliveryapp.Adapter.CartListAdapter;
import com.example.fooddeliveryapp.Helper.ManagementCart;
import com.example.fooddeliveryapp.R;

public class CartActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private TextView totalFeeTxt,taxTxt,deliveryFeeTxt,totalTxt,emptyTxt;
    private double tax,total,itemTotal;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        managementCart = new ManagementCart(this);

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

        adapter = new CartListAdapter(managementCart.getListCart(), this, () -> {
            calculateCart();
        });
        recyclerViewList.setAdapter(adapter);

        if (managementCart.getListCart().size() == 0) {
            emptyTxt.setVisibility(LinearLayout.VISIBLE);
            scrollView.setVisibility(LinearLayout.GONE);
        }else {
            emptyTxt.setVisibility(LinearLayout.GONE);
            scrollView.setVisibility(LinearLayout.VISIBLE);
        }
    }
    private void calculateCart() {
        double percentTax = 0.02;
        double deliveryFee = 10;
        tax = (double)Math.round(managementCart.getTotalFee()*percentTax *100)/100;
        itemTotal = (double)Math.round(managementCart.getTotalFee()*100)/100;
        total = (double)Math.round((itemTotal + tax + deliveryFee)*100)/100;

        totalFeeTxt.setText("$"+itemTotal);
        taxTxt.setText("$"+tax);
        deliveryFeeTxt.setText("$"+deliveryFee);
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