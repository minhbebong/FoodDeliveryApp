package com.example.fooddeliveryapp.Fragment;

import android.content.Context;
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
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.fooddeliveryapp.Adapter.CartListAdapter;
import com.example.fooddeliveryapp.Constant.GlobalConstant;
import com.example.fooddeliveryapp.Dao.AppDatabase;
import com.example.fooddeliveryapp.Entity.Cart;
import com.example.fooddeliveryapp.Entity.Food;
import com.example.fooddeliveryapp.Helper.JsonHelper;
import com.example.fooddeliveryapp.Helper.UserHelper;
import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.Service.CartService;

import java.util.List;

public class CartFragment extends Fragment {
    private RecyclerView recyclerViewList;
    private TextView totalFeeTxt,taxTxt,deliveryFeeTxt,totalTxt,emptyTxt;
    private double tax,total,itemTotal;
    private ScrollView scrollView;

    private AppDatabase db;
    public CartFragment() {
    }


    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = AppDatabase.getInstance(view.getContext());

        initView(view);
        initList(view.getContext());
    }

    private void initList(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);

        new Thread(() -> {
            Cart cart = db.cartDao().findByUserId(UserHelper.getCurrentUserId(context));
            getActivity().runOnUiThread(() -> {
                if (cart == null || cart.getCartSize() == 0) {
                    emptyTxt.setVisibility(LinearLayout.VISIBLE);
                    scrollView.setVisibility(LinearLayout.GONE);
                }else {
                    List<Food> listFood = JsonHelper.parseJsonToList(cart.getListFood(), Food.class);
                    RecyclerView.Adapter adapter = new CartListAdapter(listFood, changedFood -> {
                        new Thread(() -> {
                            Cart newCart = CartService.UpdateInCart(changedFood,db,context);
                            getActivity().runOnUiThread(() -> {
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
        tax = (double)Math.round(totalFee* GlobalConstant.PERCENT_TAX *100)/100;
        itemTotal = (double)Math.round(totalFee*100)/100;
        total = (double)Math.round((itemTotal + tax + GlobalConstant.DELIVERY_FEE)*100)/100;

        totalFeeTxt.setText("$"+itemTotal);
        taxTxt.setText("$"+tax);
        deliveryFeeTxt.setText("$"+ GlobalConstant.DELIVERY_FEE);
        totalTxt.setText("$"+total);

    }

    private void initView(View view) {
        totalFeeTxt = view.findViewById(R.id.totalFeeTxt);
        taxTxt = view.findViewById(R.id.taxTxt);
        deliveryFeeTxt = view.findViewById(R.id.deliveryFeeTxt);
        totalTxt = view.findViewById(R.id.totalTxt);
        recyclerViewList = view.findViewById(R.id.view);
        scrollView = view.findViewById(R.id.scrollView);
        emptyTxt = view.findViewById(R.id.emptyTxt);
    }
}