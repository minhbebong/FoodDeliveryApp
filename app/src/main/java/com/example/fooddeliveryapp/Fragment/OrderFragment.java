package com.example.fooddeliveryapp.Fragment;

import static com.example.fooddeliveryapp.Helper.UserHelper.getCurrentUserId;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fooddeliveryapp.Activity.OrderDetails;
import com.example.fooddeliveryapp.Adapter.CartListAdapter;
import com.example.fooddeliveryapp.Adapter.OrderAdapter;
import com.example.fooddeliveryapp.Dao.OrderDao;
import com.example.fooddeliveryapp.Entity.Food;
import com.example.fooddeliveryapp.Entity.Order;
import com.example.fooddeliveryapp.Helper.JsonHelper;
import com.example.fooddeliveryapp.Interface.MyCallBack;
import com.example.fooddeliveryapp.Interface.OnItemClickListener;
import com.example.fooddeliveryapp.R;
import com.google.firebase.database.DatabaseError;

import java.util.List;

public class OrderFragment extends Fragment implements OnItemClickListener<Order> {
    private RecyclerView recyclerViewList;
    private TextView emptyTxt;
    public OrderFragment() {
    }

    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewList = view.findViewById(R.id.order_view);
        emptyTxt = view.findViewById(R.id.emptyTxt2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        OrderDao.getInstance().getUserOrders(getCurrentUserId(view.getContext()),new MyCallBack<List<Order>>() {

            @Override
            public void onLoaded(List<Order> data) {
                if (data == null || data.size() == 0) {
                    emptyTxt.setVisibility(LinearLayout.VISIBLE);
                    recyclerViewList.setVisibility(LinearLayout.GONE);
                }else {
                    RecyclerView.Adapter adapter = new OrderAdapter(data, OrderFragment.this);
                    recyclerViewList.setAdapter(adapter);
                    emptyTxt.setVisibility(LinearLayout.GONE);
                    recyclerViewList.setVisibility(LinearLayout.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(view.getContext(), "Server Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(Order order) {
        startActivity(new Intent(getContext(), OrderDetails.class).putExtra("order", order));
    }
}