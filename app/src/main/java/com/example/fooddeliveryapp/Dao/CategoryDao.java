package com.example.fooddeliveryapp.Dao;

import com.example.fooddeliveryapp.Entity.Category;
import com.example.fooddeliveryapp.Interface.MyCallBack;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    //signleton
    private static CategoryDao instance;
    private CategoryDao(){}
    public static CategoryDao getInstance(){
        if(instance == null){
            instance = new CategoryDao();
        }
        return instance;
    }

    //get all category
    public void getAllCategory(MyCallBack<List<Category>> myCallBack) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("categories");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Category> categories = new ArrayList<>();
                for (DataSnapshot category: dataSnapshot.getChildren()) {
                    Category temp = category.getValue(Category.class);
                    categories.add(temp);
                }
                myCallBack.onLoaded(categories);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                myCallBack.onCancelled(databaseError);
            }
        });
    }
}
