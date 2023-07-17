package com.example.fooddeliveryapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fooddeliveryapp.Activity.IntroActivity;
import com.example.fooddeliveryapp.Constant.GlobalConstant;
import com.example.fooddeliveryapp.R;

public class SettingFragment extends Fragment {

    public SettingFragment() {
        // Required empty public constructor
    }


    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_privacy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến màn hình Chính sách bảo mật
                Intent intent = new Intent(getActivity(), PrivacyFragment.class);
                startActivity(intent);
            }
        });


        view.findViewById(R.id.btn_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ContactFragment.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.btn_about_us).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), AboutUsFragment.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.btn_faq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), FaqFragment.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.btn_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void logout() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(GlobalConstant.PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Intent intent = new Intent(requireActivity(), IntroActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }
}




