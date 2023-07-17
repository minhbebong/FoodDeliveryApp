package com.example.fooddeliveryapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fooddeliveryapp.Entity.Order;
import com.example.fooddeliveryapp.R;

public class OrderDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        Order order = (Order) getIntent().getSerializableExtra("order");
        System.out.println(order.getId());
    }
}