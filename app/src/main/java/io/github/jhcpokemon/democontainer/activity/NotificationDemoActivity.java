package io.github.jhcpokemon.democontainer.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import io.github.jhcpokemon.democontainer.R;

public class NotificationDemoActivity extends AppCompatActivity {

    protected Button send_notice_button;
    protected Bitmap bmp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_demo);
        send_notice_button = (Button) findViewById(R.id.send_notice);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ange);
        send_notice_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Intent intent = new Intent(NotificationDemoActivity.this, NotificationActivity.class);
                PendingIntent pi = PendingIntent.getActivity(NotificationDemoActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                Notification.Builder mBuilder = new Notification.Builder(NotificationDemoActivity.this)
                        .setContentTitle("This is title")
                        .setContentText("This is content")
                        .setContentIntent(pi)
                        .setSmallIcon(R.drawable.small)
                        .setLargeIcon(bmp);
                Notification notification = mBuilder.build();
                notificationManager.notify(1, notification);
            }
        });
    }
}
