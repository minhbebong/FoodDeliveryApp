package com.example.fooddeliveryapp.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.fooddeliveryapp.Fragment.CartFragment;
import com.example.fooddeliveryapp.Fragment.HomeFragment;
import com.example.fooddeliveryapp.Fragment.OrderFragment;
import com.example.fooddeliveryapp.Fragment.ProfileFragment;
import com.example.fooddeliveryapp.Fragment.SettingFragment;
import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    HomeFragment homeFragment;
    CartFragment cartFragment;
    SettingFragment settingFragment;
    OrderFragment orderFragment;
    ProfileFragment profileFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createFragment();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(homeFragment);
        binding.bottomNav.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.nav_home) {
                replaceFragment(homeFragment);
            }else if(item.getItemId() == R.id.nav_cart) {
                replaceFragment(cartFragment);
            }else if(item.getItemId() == R.id.nav_profile) {
                replaceFragment(profileFragment);
            }else if(item.getItemId() == R.id.nav_orders) {
                replaceFragment(orderFragment);
            }else if(item.getItemId() == R.id.nav_settings) {
                replaceFragment(settingFragment);
            }
            return true;
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fr_container, fragment);
        transaction.commit();
    }

    private void createFragment() {
        homeFragment = new HomeFragment();
        cartFragment = new CartFragment();
        settingFragment = new SettingFragment();
        orderFragment = new OrderFragment();
        profileFragment = new ProfileFragment();
    }
}