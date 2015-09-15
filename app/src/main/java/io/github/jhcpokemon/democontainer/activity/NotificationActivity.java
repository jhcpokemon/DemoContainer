package io.github.jhcpokemon.democontainer.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;

import io.github.jhcpokemon.democontainer.R;

/**
 * Created by jhcpokemon on 04/07/15.
 */
public class NotificationActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(1);
    }
}
