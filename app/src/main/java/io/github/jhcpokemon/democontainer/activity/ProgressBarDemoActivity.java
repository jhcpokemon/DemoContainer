package io.github.jhcpokemon.democontainer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;

import io.github.jhcpokemon.democontainer.R;

public class ProgressBarDemoActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private SeekBar seekBar;
    private RatingBar ratingBar;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_demo);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        ratingBar = (RatingBar) findViewById(R.id.rating_bar);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new ButtonListener());
        seekBar.setOnSeekBarChangeListener(new SeekBarListener());
        ratingBar.setOnRatingBarChangeListener(new RateBarListener());
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

    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            progressBar.incrementProgressBy(10);
        }
    }

    class SeekBarListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Log.i("SEEK_BAR", "progress ----> " + progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    class RateBarListener implements RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            Log.i("RATING_BAR", "rating -----> " + rating);
        }
    }
}
