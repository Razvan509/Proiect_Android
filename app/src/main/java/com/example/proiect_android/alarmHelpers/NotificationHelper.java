package com.example.proiect_android.alarmHelpers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.proiect_android.R;

public class NotificationHelper extends ContextWrapper {
    public static final String channelId = "channelId";
    public static final String channelName = "channel";

    private NotificationManager mManager;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotificationHelper(Context base) {
        super(base);
        createChannel();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setLightColor(R.color.colorPrimaryDark);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification(String title, String message) {
        return new NotificationCompat.Builder( getApplicationContext(), channelId)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.alarm);
    }
}