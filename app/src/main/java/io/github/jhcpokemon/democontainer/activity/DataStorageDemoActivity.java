package io.github.jhcpokemon.democontainer.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import io.github.jhcpokemon.democontainer.R;

public class DataStorageDemoActivity extends AppCompatActivity {

    private static final String CHECK_BOX_KEY = "CheckBoxValue";
    private static final String EDIT_TEXT_KEY = "EditTextContent";
    private SharedPreferences sharedPreferences;
    private EditText editText;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_stroage_demo);
        editText = (EditText) findViewById(R.id.editText);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        sharedPreferences = getPreferences(MODE_APPEND);
        if (sharedPreferences != null) {
            Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
            editText.setText(sharedPreferences.getString(EDIT_TEXT_KEY, ""));
            checkBox.setChecked(sharedPreferences.getBoolean(CHECK_BOX_KEY, false));
        } else {
            Toast.makeText(this, "No saveData", Toast.LENGTH_SHORT).show();
        }
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
    public void onBackPressed() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EDIT_TEXT_KEY, editText.getText().toString());
        editor.putBoolean(CHECK_BOX_KEY, checkBox.isChecked());
        editor.apply();
        finish();
    }
}
