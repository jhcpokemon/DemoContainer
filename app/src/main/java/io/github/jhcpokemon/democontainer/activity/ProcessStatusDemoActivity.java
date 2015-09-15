package io.github.jhcpokemon.democontainer.activity;

import android.app.ActivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import io.github.jhcpokemon.democontainer.R;

public class ProcessStatusDemoActivity extends AppCompatActivity {

    public static final String TAG = "PROCESS_DEMO";
    public ActivityManager.RunningAppProcessInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_status_demo);
        initCurrentInfo();
        Log.i(TAG, convertImportanceToString(info.importance));
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
    public void onStart() {
        super.onStart();
        initCurrentInfo();
        Log.i(TAG, convertImportanceToString(info.importance));
    }

    @Override
    public void onRestart() {
        super.onRestart();
        initCurrentInfo();
        Log.i(TAG, convertImportanceToString(info.importance));
    }

    @Override
    public void onPause() {
        super.onPause();
        initCurrentInfo();
        Log.i(TAG, convertImportanceToString(info.importance));
    }

    @Override
    public void onResume() {
        super.onResume();
        initCurrentInfo();
        Log.i(TAG, convertImportanceToString(info.importance));
    }

    @Override
    public void onStop() {
        super.onStop();
        initCurrentInfo();
        Log.i(TAG, convertImportanceToString(info.importance));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        initCurrentInfo();
        Log.i(TAG, convertImportanceToString(info.importance));
    }

    public String convertImportanceToString(int importance) {
        String result = null;
        switch (importance) {
            case ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND:
                result = "ForeGround";
                break;
            case ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE:
                result = "Visible";
                break;
            case ActivityManager.RunningAppProcessInfo.IMPORTANCE_SERVICE:
                result = "Service";
                break;
            case ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND:
                result = "BackGround";
                break;
            case ActivityManager.RunningAppProcessInfo.IMPORTANCE_EMPTY:
                result = "Empty";
                break;
            default:
                break;
        }
        return result;
    }

    public void initCurrentInfo() {
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfoList = am.getRunningAppProcesses();
        for (int i = 0; i < processInfoList.size(); i++) {
            if (processInfoList.get(i).processName.equals("io.github.jhcpokemon.democontainer")) {
                info = processInfoList.get(i);
            }
        }
    }
}
