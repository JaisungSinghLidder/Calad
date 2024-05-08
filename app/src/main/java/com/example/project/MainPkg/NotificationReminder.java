package com.example.project.MainPkg;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;

import com.example.project.MainPkg.MainActivity;
import com.example.project.R;

import java.util.Calendar;
import java.util.Random;

public class NotificationReminder extends Service {

    public static final String CHANNEL_ID = "NOTIFICATION_REMINDER";

    //Method that creates and calls the notification builder
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 10, notificationIntent, PendingIntent.FLAG_MUTABLE);
        android.app.Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Calad - The Healthy Calendar App")
                .setContentText(notificationOutput())
                .setSmallIcon(R.drawable.logo)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
        startForeground(1, notification);
        return START_NOT_STICKY;
    }

    //Method that checks for device api, if it's at least Oreo or higher, display the notification.
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = getSystemService(NotificationManager.class);
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            manager.createNotificationChannel(serviceChannel);
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    //method to check if the day is friday, used for only sending notifications on this day.
    public static boolean isNotificationDay() {
        Calendar calendar = Calendar.getInstance();
        int userDay = Calendar.WEDNESDAY;
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == userDay;
    }

    //Method to randomize and choose a random notification string to output
    public String notificationOutput() {
        String output = "";
        Random rand = new Random();
        int randomString = rand.nextInt(5) + 1;

        if (randomString == 1) {
            output = "Don't forget to place your deliveries!";
        } else if (randomString == 2) {
            output = "Choose nutrient-rich options for a satisfying meal";
        } else if (randomString == 3) {
            output = "Check your calendar for any upcoming deliveries";
        } else if (randomString == 4) {
            output = "Time for a healthy snack! Choose wisely";
        } else {
            output = "Stay positive and keep moving forward!";
        }
        return output;
    }
}