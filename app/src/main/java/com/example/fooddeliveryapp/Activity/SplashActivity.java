package com.example.fooddeliveryapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.fooddeliveryapp.Helper.UserHelper;
import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.Service.FirebaseNotificationService;

public class SplashActivity extends AppCompatActivity {

    private Thread backgroundThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent serviceIntent = new Intent(this, FirebaseNotificationService.class);
        startService(serviceIntent);

        // Start your long-running task in a separate thread
        backgroundThread = new Thread(() -> {
            // Perform your background task here

            // Check if the user is logged in
            boolean isLoggedIn = checkLoggedInStatus();

            // Redirect to the appropriate activity on the main thread
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Intent intent;
                    if (isLoggedIn) {
                        // User is logged in, start the main activity
                        intent = new Intent(SplashActivity.this, MainActivity.class);
                    } else {
                        // User is not logged in, start the Get Started activity
                        intent = new Intent(SplashActivity.this, IntroActivity.class);
                    }
                    startActivity(intent);

                    // Finish the splash activity
                    finish();
                }
            });
        });

        // Start the background thread
        backgroundThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Make sure to interrupt the background thread if the activity is destroyed
        if (backgroundThread != null) {
            backgroundThread.interrupt();
            backgroundThread = null;
        }
    }

    private boolean checkLoggedInStatus() {
        return !UserHelper.getCurrentUserId(this).equals("");
    }
}