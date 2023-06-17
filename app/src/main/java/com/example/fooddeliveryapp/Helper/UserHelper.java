package com.example.fooddeliveryapp.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.fooddeliveryapp.constant.GlobalConstant;

public class UserHelper {
    public static int getCurrentUserId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(GlobalConstant.PREFS_NAME, Context.MODE_PRIVATE);
        return Integer.parseInt(sharedPreferences.getString(GlobalConstant.CURRENT_USER,""));
    }
}
