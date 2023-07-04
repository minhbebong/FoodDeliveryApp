package com.example.fooddeliveryapp.Dao;

import com.example.fooddeliveryapp.Entity.Cart;
import com.example.fooddeliveryapp.Entity.Category;
import com.example.fooddeliveryapp.Interface.MyCallBack;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartDao {
    private static CartDao instance;
    private CartDao() {
    }
    public static CartDao getInstance() {
        if(instance == null) {
            instance = new CartDao();
        }
        return instance;
    }

    public void save(Cart cart) {
        FirebaseDatabase.getInstance().getReference("carts").child(cart.getId()).setValue(cart);
    }

    public void getUserCart(String uid, MyCallBack<Cart> myCallBack) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("carts");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Cart myCart = null;
                for (DataSnapshot category: dataSnapshot.getChildren()) {
                    Cart temp = category.getValue(Cart.class);
                    if(temp.getUserId().equals(uid)){
                        myCart = temp;
                        break;
                    }
                }
                myCallBack.onLoaded(myCart);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                myCallBack.onCancelled(databaseError);
            }
        });
    }

}
