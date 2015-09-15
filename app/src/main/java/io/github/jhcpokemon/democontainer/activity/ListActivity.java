package io.github.jhcpokemon.democontainer.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

import io.github.jhcpokemon.democontainer.R;

public class ListActivity extends android.app.ListActivity {
    private ListView listView;
    //private ArrayAdapter<String> adapter;
    private ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = getListView();
//        String[] list = {"First","Second","Third"};
//        Collections.addAll(data, list);
//        adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_single_choice,data);
//        setListAdapter(adapter);
//        (findViewById(R.id.edit_text)).setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP){
//                    data.add(((EditText)v).getText().toString());
//                    adapter.notifyDataSetChanged();
//                    ((EditText)v).setText(null);
//                }
//                return false;
//            }
//        });
        Cursor cursor = getContentResolver().query(Contacts.Phones.CONTENT_URI, null, null, null, null);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.custom_item, cursor, new String[]{Contacts.Phones._ID, Contacts.Phones.NAME}, new int[]{R.id.text_view1, R.id.text_view2});
        setListAdapter(adapter);
        cursor.close();
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
