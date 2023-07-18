package com.example.fooddeliveryapp.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.fooddeliveryapp.Constant.GlobalConstant;
import com.example.fooddeliveryapp.Entity.User;

public class UserHelper {
    public static String getCurrentUserId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(GlobalConstant.PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(GlobalConstant.CURRENT_USER,"");
    }

    public static User getCurrentUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(GlobalConstant.PREFS_NAME, Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString(GlobalConstant.USER_OBJ,"");
        return JsonHelper.parseJsonToObject(userJson, User.class);
    }
}
