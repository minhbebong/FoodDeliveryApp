package com.example.fooddeliveryapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.fooddeliveryapp.Constant.GlobalConstant;
import com.example.fooddeliveryapp.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //set current user
        SharedPreferences sharedPreferences = getSharedPreferences(GlobalConstant.PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(GlobalConstant.CURRENT_USER, "1");
        editor.apply();
    }
}