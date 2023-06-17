package com.example.fooddeliveryapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryapp.Adapter.CategoryAdapter;
import com.example.fooddeliveryapp.Adapter.RecommendedAdapter;
import com.example.fooddeliveryapp.Dao.AppDatabase;
import com.example.fooddeliveryapp.Entity.Food;
import com.example.fooddeliveryapp.Helper.JsonHelper;
import com.example.fooddeliveryapp.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewCategoryList,recyclerViewPopularList;

    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = AppDatabase.getInstance(getApplicationContext());
        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
    }

    private void bottomNavigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);

        homeBtn.setOnClickListener(v -> {
           startActivity(new Intent(MainActivity.this,MainActivity.class));
        });

        cartBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,CartActivity.class));
        });
    }

    private void recyclerViewPopular() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.view2);
        recyclerViewPopularList.setLayoutManager(layoutManager);

        new Thread(() ->{
            adapter2 = new RecommendedAdapter(db.foodDao().findTop5Food());
            runOnUiThread(() -> {
                recyclerViewPopularList.setAdapter(adapter2);
            });
        }).start();

    }

    private void recyclerViewCategory() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.view1);
        recyclerViewCategoryList.setLayoutManager(layoutManager);


        new Thread(() ->{
            adapter = new CategoryAdapter(db.categoryDao().getAll());
            runOnUiThread(() -> {
                recyclerViewCategoryList.setAdapter(adapter);
            });
        }).start();

    }


}