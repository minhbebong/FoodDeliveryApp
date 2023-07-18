package com.example.fooddeliveryapp.Service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

import com.example.fooddeliveryapp.Activity.MainActivity;
import com.example.fooddeliveryapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashSet;
import java.util.Set;

public class FirebaseNotificationService extends Service {
    private DatabaseReference databaseReference;
    private Set<String> previousKeys = new HashSet<>();
    String channel_id = "notification";

    Boolean isNotification = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null; // We don't need binding in this case
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Initialize the Firebase Realtime Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference().child("advertisments");

        // Add the ValueEventListener to listen for data changes
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    String key = childSnapshot.getKey();
                    String value = childSnapshot.getValue(String.class);
                    if (isKeyNew(key) && isNotification) {
                        showNotification(key, value);
                    }
                }
                if(!isNotification) isNotification=true;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });

        return START_STICKY; // Service will be restarted if it gets terminated by the system
    }

    private boolean isKeyNew(String key) {
        if (previousKeys.contains(key)) {
            return false;
        } else {
            previousKeys.add(key);
            return true;
        }
    }

    private void showNotification(String key, String value) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channel_id,
                    "Notification", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channel_id)
                    .setContentTitle(key)
                    .setContentText(value)
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_MUTABLE));

            notificationManager.notify(1, builder.build());
        }
    }

}
