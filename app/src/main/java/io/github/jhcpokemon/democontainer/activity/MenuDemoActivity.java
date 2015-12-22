package io.github.jhcpokemon.democontainer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.jhcpokemon.democontainer.R;
import io.github.jhcpokemon.democontainer.adapter.MyAdapter;
import io.github.jhcpokemon.democontainer.model.User;

public class MenuDemoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private List<User> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = new ListView(getApplicationContext());
        setContentView(listView);
        list.add(new User(1, "One"));
        listView.setAdapter(new MyAdapter(MenuDemoActivity.this, list));
        listView.setOnItemClickListener(this);
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        menu.setHeaderIcon(R.drawable.ange);
        menu.setHeaderTitle(R.string.app_name);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item1:
                Toast.makeText(MenuDemoActivity.this, "点了第一个", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item2:
                Toast.makeText(MenuDemoActivity.this, "点了第二个", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item1:
                Toast.makeText(MenuDemoActivity.this, "点了第一个", Toast.LENGTH_SHORT).show();
                break;
            case 100:
                Toast.makeText(MenuDemoActivity.this, "点了第二个", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
