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
import com.example.fooddeliveryapp.Helper.JsonHelper;
import com.example.fooddeliveryapp.Interface.MyCallBack;
import com.example.fooddeliveryapp.Dao.UserDao;
import com.example.fooddeliveryapp.Entity.User;
import com.example.fooddeliveryapp.R;
import com.google.firebase.database.DatabaseError;

public class LoginActivity extends AppCompatActivity {
    EditText edt_email, edt_password;

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        findViewById(R.id.btn_register).setOnClickListener(v ->{
            UserDao.getInstance().findByEmail(edt_email.getText().toString(), new MyCallBack<User>() {
                @Override
                public void onLoaded(User user) {
                    if(user != null && user.getPassword().equals(edt_password.getText().toString())){
                        //set current user
                        SharedPreferences sharedPreferences = getSharedPreferences(GlobalConstant.PREFS_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(GlobalConstant.CURRENT_USER, user.getId() + "");
                        editor.putString(GlobalConstant.USER_OBJ, JsonHelper.parseObjectToJson(user));
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Email or password not correct!", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "Can't connect to the sever", Toast.LENGTH_SHORT).show();
                }
            });
        });



    }

    public void register(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}