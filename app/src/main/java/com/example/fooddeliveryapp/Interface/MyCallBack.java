package com.example.fooddeliveryapp.Interface;

import com.google.firebase.database.DatabaseError;

public  interface MyCallBack <T> {
    void onLoaded(T data);
    void onCancelled(DatabaseError databaseError);
}
