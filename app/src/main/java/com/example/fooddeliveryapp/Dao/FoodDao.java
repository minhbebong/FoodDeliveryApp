package com.example.fooddeliveryapp.Dao;

import com.example.fooddeliveryapp.Entity.Food;
import com.example.fooddeliveryapp.Interface.MyCallBack;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FoodDao {
    private static FoodDao instance;
    private FoodDao(){}
    public static FoodDao getInstance(){
        if(instance == null){
            instance = new FoodDao();
        }
        return instance;
    }

    public void getTop5Food(MyCallBack<List<Food>> myCallBack) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("foods");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Food> foods = new ArrayList<>();
                for (DataSnapshot food: dataSnapshot.getChildren()) {
                    Food temp = food.getValue(Food.class);
                    foods.add(temp);
                }
                foods.sort((o1, o2) -> {
                    if(o1.getStar() > o2.getStar()){
                        return 1;
                    }else if(o1.getStar() < o2.getStar()){
                        return -1;
                    }else{
                        return 0;
                    }
                });
                if(foods.size() < 5) {
                    myCallBack.onLoaded(foods);
                }else {
                    myCallBack.onLoaded(foods.subList(0, 5));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                myCallBack.onCancelled(databaseError);
            }
        });
    }

    public void findById(String id, MyCallBack <Food> myCallBack) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("foods");
        myRef.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Food food = dataSnapshot.getValue(Food.class);
                myCallBack.onLoaded(food);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                myCallBack.onCancelled(databaseError);
            }
        });
    }
}
