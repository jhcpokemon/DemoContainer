package io.github.jhcpokemon.democontainer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.github.jhcpokemon.democontainer.R;
import io.github.jhcpokemon.democontainer.adapter.MyAdapter;
import io.github.jhcpokemon.democontainer.model.User;


public class TextViewDemo extends AppCompatActivity {
    private TextView textView;
    private Html.ImageGetter imageGetter;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_demo);
        textView = (TextView) findViewById(R.id.text_view);
        spinner = (Spinner) findViewById(R.id.spinner);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
//        imageGetter = new Html.ImageGetter() {
//            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//            @Override
//            public Drawable getDrawable(String source) {
//                if (source != null) {
//                    BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawableForDensity(R.drawable.google, DisplayMetrics.DENSITY_DEFAULT, null);
//                    assert drawable != null;
//                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//                    return drawable;
//                }else {
//                    return null;
//                }
//            }
//        };
//        textView.setText(Html.fromHtml("<h1>Hello</h1><b>This is image</b>https://www.google.com<img src='imageUrl'/>",imageGetter,null));
        SpannableStringBuilder builder = new SpannableStringBuilder("This is sample text!");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Log.i("TAG", "CLICK");
            }
        };
        builder.setSpan(clickableSpan, 0, 5, Spannable.SPAN_COMPOSING);
        ImageSpan imageSpan = new ImageSpan(TextViewDemo.this, R.drawable.google);
        builder.setSpan(imageSpan, 19, 20, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(builder);
        //String[] data = getResources().getStringArray(R.array.spinner);
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,data);
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1, "第一个人"));
        users.add(new User(2, "第二个人"));
        users.add(new User(3, "第三个人"));
        MyAdapter adapter = new MyAdapter(getApplicationContext(), users);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) view.findViewById(R.id.text_view1)).getText().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ((SeekBar) findViewById(R.id.seek_bar)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Current progress " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
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
}
