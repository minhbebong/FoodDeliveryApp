package com.example.fooddeliveryapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fooddeliveryapp.Adapter.CartListAdapter;
import com.example.fooddeliveryapp.Adapter.FoodAdapter;
import com.example.fooddeliveryapp.Dao.FoodDao;
import com.example.fooddeliveryapp.Entity.Food;
import com.example.fooddeliveryapp.Fragment.CartFragment;
import com.example.fooddeliveryapp.Helper.JsonHelper;
import com.example.fooddeliveryapp.Interface.MyCallBack;
import com.example.fooddeliveryapp.Interface.OnItemClickListener;
import com.example.fooddeliveryapp.R;
import com.google.firebase.database.DatabaseError;

import java.util.List;

public class FoodSearchActivity extends AppCompatActivity implements OnItemClickListener<Food> {

    String cate;
    String more;
     RecyclerView recyclerViewList;
     TextView searchHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_search);
        cate = getIntent().getStringExtra("cate");
        more = getIntent().getStringExtra("more");
        binding();
        if(cate != null&& !cate.isEmpty()) {
            initDataCate();
        }else if(more != null&& !more.isEmpty()) {
            initDataMore();
        }
    }

    private void binding() {
        recyclerViewList = findViewById(R.id.order_view);
        searchHeader = findViewById(R.id.textView5);
    }

    private void initDataCate() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        searchHeader.setText("Food Search: " + cate);
        FoodDao.getInstance().searchByCategory(cate, new MyCallBack<List<Food>>() {
            @Override
            public void onLoaded(List<Food> data) {
                RecyclerView.Adapter adapter = new FoodAdapter(data, FoodSearchActivity.this);
                recyclerViewList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void initDataMore() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        searchHeader.setText("Food Search: Top 10 Foods Rate");
        FoodDao.getInstance().getTop10Food( new MyCallBack<List<Food>>() {
            @Override
            public void onLoaded(List<Food> data) {
                RecyclerView.Adapter adapter = new FoodAdapter(data, FoodSearchActivity.this);
                recyclerViewList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onItemClick(Food item) {
        startActivity(new Intent(getApplicationContext(), ShowDetailActivity.class).putExtra("fid",  item.getId()));
    }
}