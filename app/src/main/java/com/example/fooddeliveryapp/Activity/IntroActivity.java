package com.example.fooddeliveryapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.fooddeliveryapp.Dao.AppDatabase;
import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.Constant.*;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

       findViewById(R.id.startBtn).setOnClickListener(v ->{
           startActivity(new Intent(IntroActivity.this, LoginActivity.class));
           finish();
       });
    }
}