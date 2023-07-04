package com.example.fooddeliveryapp.Activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.example.fooddeliveryapp.Entity.Cart;
import com.example.fooddeliveryapp.Entity.Category;
import com.example.fooddeliveryapp.Entity.Food;
import com.example.fooddeliveryapp.Entity.Order;
import com.example.fooddeliveryapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

       findViewById(R.id.startBtn).setOnClickListener(v ->{
           startActivity(new Intent(IntroActivity.this, LoginActivity.class));
           finish();
       });
    }
}