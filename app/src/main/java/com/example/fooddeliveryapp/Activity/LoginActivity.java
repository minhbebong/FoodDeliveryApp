package com.example.fooddeliveryapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fooddeliveryapp.Constant.GlobalConstant;
import com.example.fooddeliveryapp.Dao.AppDatabase;
import com.example.fooddeliveryapp.Entity.User;
import com.example.fooddeliveryapp.R;

public class LoginActivity extends AppCompatActivity {
    EditText edt_email, edt_password;
    AppDatabase db;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
    db= AppDatabase.getInstance(this);
        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(() -> {
                    user =db.userDao().findByEmail(edt_email.getText().toString());
                    if(user != null && user.password.equals(edt_password.getText().toString())){
                        //set current user
                        SharedPreferences sharedPreferences = getSharedPreferences(GlobalConstant.PREFS_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(GlobalConstant.CURRENT_USER, user.getId() + "");
                        editor.apply();
                       runOnUiThread(() ->{
                           startActivity(new Intent(LoginActivity.this, MainActivity.class));
                           finish();
                       });
                    }
                    else {
                        runOnUiThread(() -> {
                            Toast.makeText(getApplicationContext(), "Email or password not correct!", Toast.LENGTH_SHORT).show();
                        });
                    }
                }).start();

            }
        });



    }

    public void register(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}