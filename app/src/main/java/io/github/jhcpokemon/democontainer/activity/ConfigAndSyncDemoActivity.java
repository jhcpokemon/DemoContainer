package io.github.jhcpokemon.democontainer.activity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import io.github.jhcpokemon.democontainer.R;

public class ConfigAndSyncDemoActivity extends AppCompatActivity {

    private static final String TAG = "LifeCycle log";
    private ProgressBar progressBar;
    private EditText showHtml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "On Create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_and_sync_demo);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setProgress(0);
        showHtml = (EditText) findViewById(R.id.show_html);
        findViewById(R.id.start_async_task_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskNetWork task = new AsyncTaskNetWork(progressBar);
                task.execute();
            }
        });
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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            Log.i(TAG, "Portrait");
        } else if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
            Log.i(TAG, "Landscape");
        }
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "On Destroy");
        super.onDestroy();
    }

    class AsyncTaskNetWork extends AsyncTask<Void, Integer, String> {
        private ProgressBar progressBar;

        public AsyncTaskNetWork(ProgressBar progressBar) {
            this.progressBar = progressBar;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.incrementProgressBy(values[0]);
        }

        @Override
        protected String doInBackground(Void... params) {
            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL("https://www.baidu.com/");
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            showHtml.setText(s);
            progressBar.setProgress(100);
        }
    }
}
