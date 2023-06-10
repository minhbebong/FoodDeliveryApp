package com.example.fooddeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

       findViewById(R.id.startBtn).setOnClickListener(v ->{
           startActivity(new Intent(IntroActivity.this,MainActivity.class));
       });

    }
}