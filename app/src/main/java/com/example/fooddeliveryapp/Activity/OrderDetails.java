package com.example.fooddeliveryapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.fooddeliveryapp.Adapter.OrderDetailsAdapter;
import com.example.fooddeliveryapp.Constant.GlobalConstant;
import com.example.fooddeliveryapp.Entity.Cart;
import com.example.fooddeliveryapp.Entity.Order;
import com.example.fooddeliveryapp.R;

public class OrderDetails extends AppCompatActivity {

    Order order;
    TextView orderId;
     RecyclerView recyclerViewList;

    private TextView totalFeeTxt,taxTxt,deliveryFeeTxt,totalTxt;
    private double tax,delivery,total,itemTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        order = (Order) getIntent().getSerializableExtra("order");
        binding();
        loadData();
    }
    private void binding(){
        totalTxt = findViewById(R.id.totalTxt);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryFeeTxt = findViewById(R.id.deliveryFeeTxt);

        orderId = findViewById(R.id.order_header);
        recyclerViewList = findViewById(R.id.view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
    }

    private void loadData(){
        orderId.setText("Order#: "+order.getId().substring(0,6));
        RecyclerView.Adapter adapter = new OrderDetailsAdapter(order.parseListFood());
        recyclerViewList.setAdapter(adapter);
        calculateCart();
    }

    private void calculateCart() {
        if(order == null) return;
        double totalFee = order.totalFee();
        tax = (double)Math.round(totalFee* GlobalConstant.PERCENT_TAX *100)/100;
        delivery = (double)Math.round(totalFee* GlobalConstant.DELIVERY_FEE *100)/100;
        itemTotal = (double)Math.round(totalFee*100)/100;
        total = (double)Math.round((itemTotal + tax + delivery)*100)/100;

        totalFeeTxt.setText("$"+itemTotal);
        taxTxt.setText("$"+tax);
        deliveryFeeTxt.setText("$"+ delivery);
        totalTxt.setText("$"+total);

    }
}