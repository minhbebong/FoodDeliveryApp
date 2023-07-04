package com.example.fooddeliveryapp.Dao;

import com.example.fooddeliveryapp.Entity.Order;
import com.example.fooddeliveryapp.Interface.MyCallBack;

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
    public void getUserOrders(String uid, MyCallBack<List<Order>> myCallBack){
        //TODO: Tham khao theo cac dao khac de viet
    }

    public void findById(String id, MyCallBack<Order> myCallBack){
        //TODO: Tham khao theo cac dao khac de viet
    }
}
