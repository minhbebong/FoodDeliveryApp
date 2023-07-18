package com.example.fooddeliveryapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fooddeliveryapp.Activity.EditProfile;
import com.example.fooddeliveryapp.Activity.LoginActivity;
import com.example.fooddeliveryapp.Activity.MainActivity;
import com.example.fooddeliveryapp.Activity.OrderDetails;
import com.example.fooddeliveryapp.Dao.UserDao;
import com.example.fooddeliveryapp.Entity.User;
import com.example.fooddeliveryapp.Helper.UserHelper;
import com.example.fooddeliveryapp.Interface.MyCallBack;
import com.example.fooddeliveryapp.R;
import com.google.firebase.database.DatabaseError;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    User user;
    TextView name1,name2,email1,email2,phone,address;
    public ProfileFragment() {
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding(view);
        initData(view);
    }

    private void binding(View v) {
        name1 = v.findViewById(R.id.txt_name1);
        name2 = v.findViewById(R.id.txt_name2);
        email1 = v.findViewById(R.id.txt_email1);
        email2 = v.findViewById(R.id.txt_email2);
        phone = v.findViewById(R.id.txt_phone);
        address = v.findViewById(R.id.txt_address);
        v.findViewById(R.id.btn_edit).setOnClickListener(v1 -> {
            startActivity(new Intent(getContext(), EditProfile.class).putExtra("user", user));
        });
    }

    private void initData(View v) {
        user = UserHelper.getCurrentUser(v.getContext());
        name1.setText(user.getFullName());
        name2.setText(user.getFullName());
        email1.setText(user.getEmail());
        email2.setText(user.getEmail());
        phone.setText(user.getPhoneNumber());
        address.setText(user.getAddress());

    }

    @Override
    public void onResume() {
        super.onResume();
        initData(getView());
    }
}