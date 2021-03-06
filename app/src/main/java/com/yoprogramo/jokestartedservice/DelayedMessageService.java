package com.yoprogramo.jokestartedservice;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;


public class DelayedMessageService extends IntentService {

    public static final int NOTIFICATION_ID = 100;

    public static final String EXTRA_MESSAGE = "message";
    //private Handler handler;

    public DelayedMessageService() {
        super("DelayedMessageService");
    }


    //    This method run on the main thread and it is executed before onHandleIntent
    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        //handler = new Handler();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this) {
            try {
                wait(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (intent != null) {
            String text = intent.getStringExtra(EXTRA_MESSAGE);
            showText(text);
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void showText(final String text) {

//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
//            }
//        });

        Intent intent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent)
                .setContentText(text)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notification);

    }

}
