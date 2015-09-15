package io.github.jhcpokemon.democontainer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.jhcpokemon.democontainer.R;
import io.github.jhcpokemon.democontainer.adapter.MyRecyclerViewAdapter;
import io.github.jhcpokemon.democontainer.listener.RecyclerItemClickListener;

public class CardView_RecyclerViewDemoActivity extends AppCompatActivity {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview_recyclerview);
        ButterKnife.bind(this);

        final ArrayList<Integer> numbers = new ArrayList<>();
        numbers.addAll(Arrays.asList(R.drawable.img_01, R.drawable.img_02, R.drawable.img_03, R.drawable.img_04, R.drawable.img_05, R.drawable.img_06,
                R.drawable.img_07, R.drawable.img_08, R.drawable.img_09, R.drawable.img_10, R.drawable.img_11, R.drawable.img_12, R.drawable.img_13,
                R.drawable.img_14, R.drawable.img_15));
        final MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(getApplicationContext(), numbers);

        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        numbers.remove(position);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(getApplicationContext(),
                                ((MyRecyclerViewAdapter.ViewHolder) recyclerView.getChildViewHolder(view)).getTextViewText(),
                                Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
