package com.github.zqhcxy.glidetest.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.AppWidgetTarget;
import com.bumptech.glide.request.target.NotificationTarget;
import com.github.zqhcxy.glidetest.R;

/**
 * 加载通知栏图标
 */
public class NotificationTargetActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int NOTIFICATION_ID = 110;
    private Button notify_start;


    private NotificationTarget notificationTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_target);
        notify_start = (Button) findViewById(R.id.notify_start);
        notify_start.setOnClickListener(this);
    }


    private void initNotify() {
        final RemoteViews rv = new RemoteViews(getPackageName(), R.layout.remoteview_notification);
        rv.setImageViewResource(R.id.remoteview_notification_icon, R.mipmap.ic_service_gift);
        rv.setTextViewText(R.id.remoteview_notification_headline, "Headline");
        rv.setTextViewText(R.id.remoteview_notification_short_message, "Short Message");
        // build notification
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(NotificationTargetActivity.this)
                        .setSmallIcon(R.mipmap.ic_service_gift)
                        .setContentTitle("Content Title")
                        .setContentText("Content Text")
                        .setContent(rv)
                        .setPriority(NotificationCompat.PRIORITY_MIN);
        final Notification notification = mBuilder.build();
        // set big content view for newer androids
        if (android.os.Build.VERSION.SDK_INT >= 16) {
            notification.bigContentView = rv;
        }
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, notification);
        notificationTarget = new NotificationTarget(this, rv, R.id.remoteview_notification_icon, notification, NOTIFICATION_ID);
        Glide.with(this).load("content://mms/part/3691").asBitmap().fitCenter().into(notificationTarget);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notify_start:
                initNotify();
                break;

        }
    }

    public class FSAppWidgetProvider extends AppWidgetProvider {

        private AppWidgetTarget appWidgetTarget;

        @Override
        public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                             int[] appWidgetIds) {

            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.remoteview_notification);

            appWidgetTarget = new AppWidgetTarget(context, rv, R.id.remoteview_notification_icon, appWidgetIds);

            Glide
                    .with(context.getApplicationContext()) // safer!
                    .load("content://mms/part/3691")
                    .asBitmap()
                    .into(appWidgetTarget);

            pushWidgetUpdate(context, rv);
        }

        public void pushWidgetUpdate(Context context, RemoteViews rv) {
            ComponentName myWidget = new ComponentName(context, FSAppWidgetProvider.class);
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            manager.updateAppWidget(myWidget, rv);
        }
    }


}
