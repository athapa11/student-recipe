package com.example.studentrecipes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.example.studentrecipes.activities.MainActivity.MAIN_CHANNEL;

public class ReminderBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, MAIN_CHANNEL)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Student Recipes")
                .setContentText("New Recipes Recently Added!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(2, notification.build());
    }
}
