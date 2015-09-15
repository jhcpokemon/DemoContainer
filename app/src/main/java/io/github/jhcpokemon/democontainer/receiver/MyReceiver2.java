package io.github.jhcpokemon.democontainer.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import io.github.jhcpokemon.democontainer.activity.BroadCastReceiverDemoActivity;

/**
 * Created by jhcpokemon on 09/11/15.
 */
public class MyReceiver2 extends BroadcastReceiver {
    public MyReceiver2() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(BroadCastReceiverDemoActivity.LOG_TAG, "MyService2");
    }
}
