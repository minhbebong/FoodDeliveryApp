package com.example.fooddeliveryapp.Dao;

import androidx.annotation.NonNull;

import com.example.fooddeliveryapp.Entity.User;
import com.example.fooddeliveryapp.Interface.MyCallBack;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDao {

    private static UserDao instance;
    private UserDao(){}
    public static UserDao getInstance(){
        if(instance == null){
            instance = new UserDao();
        }
        return instance;
    }

    public void findById(String id, MyCallBack<User> myCallBack){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(id);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    User u = dataSnapshot.getValue(User.class);
                    myCallBack.onLoaded(u);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                myCallBack.onCancelled(databaseError);
            }
        };
        myRef.addListenerForSingleValueEvent(postListener);
    }
    public void findByEmail(String email, MyCallBack<User> myCallBack){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User u = null;
                for (DataSnapshot user: dataSnapshot.getChildren()) {
                    User temp = user.getValue(User.class);
                    if(temp.getEmail().equals(email)){
                        u = temp;
                        break;
                    }
                }
                myCallBack.onLoaded(u);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                myCallBack.onCancelled(databaseError);
            }
        });
    }

    public void insert(User user){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        myRef.child(user.getId()).setValue(user);
    }
}
