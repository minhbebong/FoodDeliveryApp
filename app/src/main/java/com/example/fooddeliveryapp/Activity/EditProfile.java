package com.example.fooddeliveryapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fooddeliveryapp.Dao.UserDao;
import com.example.fooddeliveryapp.Entity.User;
import com.example.fooddeliveryapp.R;

public class EditProfile extends AppCompatActivity {
    User user;
    EditText name,phone,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        user = (User) getIntent().getSerializableExtra("user");
        binding();
        loadUser();
    }
    private void binding() {
        name = findViewById(R.id.nameEditText);
        phone = findViewById(R.id.phoneEditText);
        address = findViewById(R.id.addressEditText);
        findViewById(R.id.btn_save).setOnClickListener(v -> {
            user.setFullName(name.getText().toString());
            user.setPhoneNumber(phone.getText().toString());
            user.setAddress(address.getText().toString());
            UserDao.getInstance().save(user);
            Toast.makeText(this, "Update Successfully!", Toast.LENGTH_SHORT).show();
        });
    }

    private  void loadUser(){
        name.setText(user.getFullName());
        phone.setText(user.getPhoneNumber());
        address.setText(user.getAddress());
    }
}