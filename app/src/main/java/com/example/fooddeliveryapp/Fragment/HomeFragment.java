package com.example.fooddeliveryapp.Fragment;

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

import com.example.fooddeliveryapp.Adapter.CategoryAdapter;
import com.example.fooddeliveryapp.Adapter.RecommendedAdapter;
import com.example.fooddeliveryapp.Dao.AppDatabase;
import com.example.fooddeliveryapp.Entity.User;
import com.example.fooddeliveryapp.Helper.UserHelper;
import com.example.fooddeliveryapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewCategoryList,recyclerViewPopularList;
    private TextView txtWelcome;
    private AppDatabase db;

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
        db = AppDatabase.getInstance(view.getContext());
        welcomeText(view);
        recyclerViewCategory(view);
        recyclerViewPopular(view);
    }

    private void recyclerViewPopular(View view) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = view.findViewById(R.id.view2);
        recyclerViewPopularList.setLayoutManager(layoutManager);

        new Thread(() ->{
            adapter2 = new RecommendedAdapter(db.foodDao().findTop5Food());
            getActivity().runOnUiThread(() -> {
                recyclerViewPopularList.setAdapter(adapter2);
            });
        }).start();
    }

    private void recyclerViewCategory(View view) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerViewCategoryList = view.findViewById(R.id.view1);
        recyclerViewCategoryList.setHasFixedSize(true);
        recyclerViewCategoryList.setLayoutManager(layoutManager);


        new Thread(() ->{
            adapter = new CategoryAdapter(db.categoryDao().getAll());
            getActivity().runOnUiThread(() -> {
                recyclerViewCategoryList.setAdapter(adapter);
            });
        }).start();

    }

    private void welcomeText(View view) {
        txtWelcome = view.findViewById(R.id.txt_welcome);
        new Thread(() ->{
            User currentUser = db.userDao().findById(UserHelper.getCurrentUserId(view.getContext()));
            getActivity().runOnUiThread(() -> {
                txtWelcome.setText("Welcome, "+ currentUser.getFullName());
            });
        }).start();
    }
}