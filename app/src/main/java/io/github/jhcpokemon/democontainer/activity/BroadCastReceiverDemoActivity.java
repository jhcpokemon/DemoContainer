package io.github.jhcpokemon.democontainer.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import io.github.jhcpokemon.democontainer.R;
import io.github.jhcpokemon.democontainer.receiver.MyReceiver1;

public class BroadCastReceiverDemoActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String LOG_TAG = "MY_LOG_TAG";
    private static final String ACTION = "android.intent.action.MY_ACTION";
    private MyReceiver1 receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast_receiver_demo);
        findViewById(R.id.send_broadcast_btn).setOnClickListener(this);
        findViewById(R.id.register_btn).setOnClickListener(this);
        findViewById(R.id.unregister_btn).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_broadcast_btn:
                Intent intent = new Intent(ACTION);
                sendOrderedBroadcast(intent, null);
                break;
            case R.id.register_btn:
                if (receiver == null) {
                    receiver = new MyReceiver1();
                }
                registerReceiver(receiver, new IntentFilter(ACTION));
                break;
            case R.id.unregister_btn:
                if (receiver != null) {
                    unregisterReceiver(receiver);
                }
                break;
            default:
                break;
        }
    }
}
