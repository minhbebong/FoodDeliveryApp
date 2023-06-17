package com.example.fooddeliveryapp.Dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.fooddeliveryapp.Entity.*;
import com.example.fooddeliveryapp.Helper.DbHelper;

@Database(entities = {User.class, Category.class, Food.class, Cart.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
        public static synchronized AppDatabase getInstance(Context context) {

            instance = androidx.room.Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "food-db"
            ).allowMainThreadQueries().build();
            if (!context.getDatabasePath("food-db").exists()) {
                DbHelper.initDb(instance);
            }
            return instance;
    }
    public abstract UserDao userDao();
    public abstract CategoryDao categoryDao();
    public abstract FoodDao foodDao();
    public abstract CartDao cartDao();
}
