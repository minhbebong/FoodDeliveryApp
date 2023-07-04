package com.example.fooddeliveryapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fooddeliveryapp.Interface.MyCallBack;
import com.example.fooddeliveryapp.Dao.UserDao;
import com.example.fooddeliveryapp.Entity.User;
import com.example.fooddeliveryapp.R;
import com.google.firebase.database.DatabaseError;

import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {
    EditText edt_fullname, edt_email, edt_phonenumber,edt_password, edt_address;
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

        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_fullname.getText().toString().equals("")||edt_address.getText().toString().equals("")||
                edt_email.getText().toString().equals("")||edt_password.getText().toString().equals("")||edt_phonenumber.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please field all information!", Toast.LENGTH_SHORT).show();
                    return;
                }
                UserDao.getInstance().findByEmail(edt_email.getText().toString(), new MyCallBack<User>() {
                    @Override
                    public void onLoaded(User user) {
                        if(user != null ){
                            Toast.makeText(getApplicationContext(), "User already exists!", Toast.LENGTH_SHORT).show();
                        }else {
                            String id = UUID.randomUUID().toString();
                            user = new User();
                            user.setId(id);
                            user.setEmail(edt_email.getText().toString());
                            user.setFullName(edt_fullname.getText().toString());
                            user.setAddress(edt_address.getText().toString());
                            user.setPassword(edt_password.getText().toString());
                            user.setPhoneNumber(edt_phonenumber.getText().toString());
                            UserDao.getInstance().insert(user);
                            runOnUiThread(() -> {
                                Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_SHORT).show();
                                finish();
                            });
                        }

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Can't connect to the sever", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });






    }

    public void login(View view) {
        finish();
    }

}