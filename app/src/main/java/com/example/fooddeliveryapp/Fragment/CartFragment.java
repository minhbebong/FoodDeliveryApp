package com.example.fooddeliveryapp.Fragment;

import static com.example.fooddeliveryapp.Helper.UserHelper.getCurrentUserId;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fooddeliveryapp.Adapter.CartListAdapter;
import com.example.fooddeliveryapp.Constant.GlobalConstant;
import com.example.fooddeliveryapp.Dao.CartDao;
import com.example.fooddeliveryapp.Entity.Cart;
import com.example.fooddeliveryapp.Entity.Food;
import com.example.fooddeliveryapp.Helper.JsonHelper;
import com.example.fooddeliveryapp.Interface.CartChange;
import com.example.fooddeliveryapp.Interface.MyCallBack;
import com.example.fooddeliveryapp.R;
import com.google.firebase.database.DatabaseError;

import java.util.List;

public class CartFragment extends Fragment implements CartChange {
    private RecyclerView recyclerViewList;
    private TextView totalFeeTxt,taxTxt,deliveryFeeTxt,totalTxt,emptyTxt;
    private double tax,total,itemTotal;
    private ConstraintLayout bill;


    public CartFragment() {
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
        initView(view);
        initList(view.getContext());
    }

    private void initList(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        CartDao.getInstance().getUserCart(getCurrentUserId(context), new MyCallBack<Cart>() {
            @Override
            public void onLoaded(Cart cart) {
                if (cart == null || cart.cartSize() == 0) {
                    emptyTxt.setVisibility(LinearLayout.VISIBLE);
                    bill.setVisibility(LinearLayout.GONE);
                }else {
                    List<Food> listFood = JsonHelper.parseJsonToList(cart.getListFood(), Food.class);
                    RecyclerView.Adapter adapter = new CartListAdapter(listFood, CartFragment.this);
                    calculateCart(cart);
                    recyclerViewList.setAdapter(adapter);
                    emptyTxt.setVisibility(LinearLayout.GONE);
                    bill.setVisibility(LinearLayout.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(context, "Server Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void calculateCart(Cart cart) {
        if(cart == null) return;
        double totalFee = cart.totalFee();
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
        bill = view.findViewById(R.id.Bill);
        emptyTxt = view.findViewById(R.id.emptyTxt);
    }

    @Override
    public void execute(Food changedFood) {
        CartDao.getInstance().getUserCart(getCurrentUserId(getContext()), new MyCallBack<Cart>() {
            @Override
            public void onLoaded(Cart cart) {
                if (cart !=null) {
                    List<Food> foods = JsonHelper.parseJsonToList(cart.getListFood(),Food.class);
                    for(Food f : foods) {
                        if(f.getId().equals(changedFood.getId())) {
                            if(changedFood.getNumberInCart() == 0) {
                                foods.remove(f);
                            }else {
                                f.setNumberInCart(changedFood.getNumberInCart());
                            }
                            break;
                        }
                    }
                    String listFoodJson = JsonHelper.parseListToJson(foods);
                    cart.setListFood(listFoodJson);
                    CartDao.getInstance().save(cart);
                    if (cart.cartSize() == 0) {
                        emptyTxt.setVisibility(LinearLayout.VISIBLE);
                        bill.setVisibility(LinearLayout.GONE);
                    }
                    calculateCart(cart);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), "Sever Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}