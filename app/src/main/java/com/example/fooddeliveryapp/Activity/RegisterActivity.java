package com.example.fooddeliveryapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fooddeliveryapp.Dao.AppDatabase;
import com.example.fooddeliveryapp.Entity.User;
import com.example.fooddeliveryapp.R;

public class RegisterActivity extends AppCompatActivity {
    EditText edt_fullname, edt_email, edt_phonenumber,edt_password, edt_address;
    AppDatabase db;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edt_fullname = findViewById(R.id.edt_fullname);
        edt_email = findViewById(R.id.edt_email);
        edt_phonenumber = findViewById(R.id.edt_phonenumber);
        edt_password = findViewById(R.id.edt_password);
        edt_address = findViewById(R.id.edt_address);
        db = AppDatabase.getInstance(this);

        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_fullname.getText().toString().equals("")||edt_address.getText().toString().equals("")||
                edt_email.getText().toString().equals("")||edt_password.getText().toString().equals("")||edt_phonenumber.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please field all information!", Toast.LENGTH_SHORT).show();
                    return;
                }
                new Thread(() -> {
                    user = db.userDao().findByEmail(edt_email.getText().toString());
                    if (user != null) {
                        runOnUiThread(() -> {
                            Toast.makeText(getApplicationContext(), "User already exists!", Toast.LENGTH_SHORT).show();
                        });
                    } else {
                        user = new User();
                        user.setEmail(edt_email.getText().toString());
                        user.setFullName(edt_fullname.getText().toString());
                        user.setAddress(edt_address.getText().toString());
                        user.setPassword(edt_password.getText().toString());
                        user.setPhoneNumber(edt_phonenumber.getText().toString());
                        db.userDao().insertAll(user);
                        runOnUiThread(() -> {
                            Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_SHORT).show();
                            finish();
                        });
                    }
                }).start();
            }
        });






    }

    public void login(View view) {
        finish();
    }

}