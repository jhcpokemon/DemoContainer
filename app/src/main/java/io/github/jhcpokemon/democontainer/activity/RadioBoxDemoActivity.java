package io.github.jhcpokemon.democontainer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import io.github.jhcpokemon.democontainer.R;

public class RadioBoxDemoActivity extends AppCompatActivity {
    private RadioButton button1, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_box_demo);
        button1 = (RadioButton) findViewById(R.id.button1);
        button2 = (RadioButton) findViewById(R.id.button2);
        button3 = (RadioButton) findViewById(R.id.button3);
        button4 = (RadioButton) findViewById(R.id.button4);
        RadioButtonListener listener = new RadioButtonListener();
        button1.setOnCheckedChangeListener(listener);
        button2.setOnCheckedChangeListener(listener);
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

    class RadioButtonListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton view, boolean isChecked) {
            RadioButton button = (RadioButton) view;
            if (button.getId() == button1.getId()) {
                if (button.isChecked()) {
                    button3.setChecked(true);
                } else {
                    button3.setChecked(false);
                }
            } else if (button.getId() == button2.getId()) {
                if (button.isChecked()) {
                    button4.setChecked(true);
                } else {
                    button4.setChecked(false);
                }
            }
        }
    }
}
