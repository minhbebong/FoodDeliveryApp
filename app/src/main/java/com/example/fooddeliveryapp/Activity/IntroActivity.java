package com.example.fooddeliveryapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.constant.GlobalConstant;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences(GlobalConstant.PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(GlobalConstant.CURRENT_USER, "1");
        editor.apply();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

       findViewById(R.id.startBtn).setOnClickListener(v ->{
           startActivity(new Intent(IntroActivity.this,MainActivity.class));
       });
    }
}