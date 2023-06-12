package com.example.fooddeliveryapp.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryapp.Adapter.CategoryAdapter;
import com.example.fooddeliveryapp.Adapter.RecommendedAdapter;
import com.example.fooddeliveryapp.Domain.CategoryDomain;
import com.example.fooddeliveryapp.Domain.FoodDomain;
import com.example.fooddeliveryapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewCategoryList,recyclerViewPopularList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewCategory();
        recyclerViewPopular();
    }

    private void recyclerViewPopular() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.view2);
        recyclerViewPopularList.setLayoutManager(layoutManager);

        ArrayList<FoodDomain> foodList = new ArrayList<>();
        foodList.add(new FoodDomain("Pepperoni Pizza", "pizza1","slices pepperoni ,mozzarella cheese, pizza sauce, pizza dough",13.0,5,20,1000));
        foodList.add(new FoodDomain("Burger", "burger","beef, cheese, tomato, onion, lettuce, ketchup, mayonnaise, mustard, pickles",10.0,4,15,800));
        foodList.add(new FoodDomain("Vegetable Pizza", "pizza3","mozzarella cheese, pizza sauce, pizza dough, onion, tomato, mushroom, bell pepper, olive",12.0,4,20,900));

        adapter2 = new RecommendedAdapter(foodList);
        recyclerViewPopularList.setAdapter(adapter2);
    }

    private void recyclerViewCategory() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.view1);
        recyclerViewCategoryList.setLayoutManager(layoutManager);

        ArrayList<CategoryDomain> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDomain("Pizza", "cat_1"));
        categoryList.add(new CategoryDomain("Burger", "cat_2"));
        categoryList.add(new CategoryDomain("HotDog", "cat_3"));
        categoryList.add(new CategoryDomain("Drink", "cat_4"));
        categoryList.add(new CategoryDomain("Donut", "cat_5"));

        adapter = new CategoryAdapter(categoryList);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}