package com.example.fooddeliveryapp.Dao;

import com.example.fooddeliveryapp.Entity.Cart;
import com.example.fooddeliveryapp.Entity.Order;
import com.example.fooddeliveryapp.Interface.MyCallBack;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    //signleton
    private static OrderDao instance;
    private OrderDao(){}
    public static OrderDao getInstance(){
        if(instance == null){
            instance = new OrderDao();
        }
        return instance;
    }

    public void save(Order order) {
        FirebaseDatabase.getInstance().getReference("orders").child(order.getId()).setValue(order);
    }
    public void getUserOrders(String uid, MyCallBack<List<Order>> myCallBack){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("orders");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Order> orderList = new ArrayList<>();
                for (DataSnapshot order: dataSnapshot.getChildren()) {
                    Order temp = order.getValue(Order.class);
                    if(temp.getUserId().equals(uid)){
                        orderList.add(temp);
                    }
                }
                myCallBack.onLoaded(orderList);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                myCallBack.onCancelled(databaseError);
            }
        });
    }

    public void findById(String id, MyCallBack<Order> myCallBack){
        //TODO: Tham khao theo cac dao khac de viet
    }
}
