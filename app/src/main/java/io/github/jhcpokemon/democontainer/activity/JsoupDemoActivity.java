package io.github.jhcpokemon.democontainer.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.jhcpokemon.democontainer.R;

public class JsoupDemoActivity extends AppCompatActivity {
    @Bind(R.id.refresh)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.list)
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    MyHandler handler = new MyHandler();
    ArrayAdapter<String> adapter;
    boolean count = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup_demo);
        ButterKnife.bind(this);
        initArrayList();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        refreshLayout.setColorSchemeResources(R.color.blue);
        refreshLayout.setProgressBackgroundColorSchemeResource(R.color.light_grey);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!count) {
                    String html = arrayList.get(0);
                    Document doc = Jsoup.parse(html);
                    arrayList.set(0, doc.getElementsByTag("html").toString());
                    String html1 = arrayList.get(1);
                    Document doc1 = Jsoup.parseBodyFragment(html1);
                    arrayList.set(1, doc1.body().toString());
                    final String url = arrayList.get(2);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (url.contains("http")) {
                                    Document doc2 = Jsoup.connect(url).get();
                                    Message msg = handler.obtainMessage();
                                    msg.obj = doc2.title();
                                    msg.what = 2;
                                    handler.sendMessage(msg);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    count = true;
                } else {
                    refreshLayout.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

    void initArrayList() {
        arrayList.add("<html><head>ccccccc</head><body><div><h1>fffffff</h1><a href=''>ddddd</a></div></body></html>");
        arrayList.add("<div><p>Lorem ipsum.</p>");
        arrayList.add("http://www.baidu.com/");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    class MyHandler extends android.os.Handler {
        @Override
        public void handleMessage(Message msg) {
            //此时最后修改arrayList内容
            arrayList.set(msg.what, msg.obj.toString());
            refreshLayout.setRefreshing(false);
            adapter.notifyDataSetChanged();
        }
    }
}
