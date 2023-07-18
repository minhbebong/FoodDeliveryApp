package com.example.fooddeliveryapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fooddeliveryapp.Activity.FoodSearchActivity;
import com.example.fooddeliveryapp.Activity.OrderDetails;
import com.example.fooddeliveryapp.Adapter.CategoryAdapter;
import com.example.fooddeliveryapp.Adapter.RecommendedAdapter;
import com.example.fooddeliveryapp.Dao.CategoryDao;
import com.example.fooddeliveryapp.Dao.FoodDao;
import com.example.fooddeliveryapp.Entity.Order;
import com.example.fooddeliveryapp.Interface.MyCallBack;
import com.example.fooddeliveryapp.Dao.UserDao;
import com.example.fooddeliveryapp.Entity.Food;
import com.example.fooddeliveryapp.Entity.User;
import com.example.fooddeliveryapp.Helper.UserHelper;
import com.example.fooddeliveryapp.Interface.OnItemClickListener;
import com.example.fooddeliveryapp.R;
import com.google.firebase.database.DatabaseError;

import java.util.List;

public class HomeFragment extends Fragment implements OnItemClickListener<String> {

    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewCategoryList,recyclerViewPopularList;
    private TextView txtWelcome;

    public HomeFragment() {
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        welcomeText(view);
        recyclerViewCategory(view);
        recyclerViewPopular(view);
        view.findViewById(R.id.btn_more).setOnClickListener(v ->{
            startActivity(new Intent(getContext(), FoodSearchActivity.class).putExtra("more", "more"));
        });
    }

    private void recyclerViewPopular(View view) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = view.findViewById(R.id.view2);
        recyclerViewPopularList.setLayoutManager(layoutManager);

        FoodDao.getInstance().getTop5Food(new MyCallBack<List<Food>>() {
            @Override
            public void onLoaded(List<Food> data) {
                adapter2 = new RecommendedAdapter(data);
                recyclerViewPopularList.setAdapter(adapter2);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), "Server Error !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void recyclerViewCategory(View view) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerViewCategoryList = view.findViewById(R.id.view1);
        recyclerViewCategoryList.setHasFixedSize(true);
        recyclerViewCategoryList.setLayoutManager(layoutManager);

        CategoryDao.getInstance().getAllCategory(new MyCallBack<List<com.example.fooddeliveryapp.Entity.Category>>() {
            @Override
            public void onLoaded(List<com.example.fooddeliveryapp.Entity.Category> data) {
                adapter = new CategoryAdapter(data,HomeFragment.this);
                recyclerViewCategoryList.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), "Server Error !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void welcomeText(View view) {
        txtWelcome = view.findViewById(R.id.txt_welcome);

        txtWelcome.setText("Welcome, "+ UserHelper.getCurrentUser(view.getContext()).getFullName());

    }

    @Override
    public void onItemClick(String category) {
        startActivity(new Intent(getContext(), FoodSearchActivity.class).putExtra("cate", category));
    }
}