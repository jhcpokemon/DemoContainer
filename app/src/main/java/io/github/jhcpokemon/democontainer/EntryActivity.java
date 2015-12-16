package io.github.jhcpokemon.democontainer;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import io.github.jhcpokemon.democontainer.activity.AnimationDemoActivity;
import io.github.jhcpokemon.democontainer.activity.BatteryDemoActivity;
import io.github.jhcpokemon.democontainer.activity.BitmapResizeDemoActivity;
import io.github.jhcpokemon.democontainer.activity.BlueToothDemoActivity;
import io.github.jhcpokemon.democontainer.activity.BroadCastReceiverDemoActivity;
import io.github.jhcpokemon.democontainer.activity.CardView_RecyclerViewDemoActivity;
import io.github.jhcpokemon.democontainer.activity.ConfigAndSyncDemoActivity;
import io.github.jhcpokemon.democontainer.activity.ContentProviderDemoActivity;
import io.github.jhcpokemon.democontainer.activity.CustomViewDemoActivity;
import io.github.jhcpokemon.democontainer.activity.DataStorageDemoActivity;
import io.github.jhcpokemon.democontainer.activity.DialogDemoActivity;
import io.github.jhcpokemon.democontainer.activity.HandlerDemoActivity;
import io.github.jhcpokemon.democontainer.activity.HttpClientDemoActivity;
import io.github.jhcpokemon.democontainer.activity.ImageSwitcherDemoActivity;
import io.github.jhcpokemon.democontainer.activity.IntentDemoActivity;
import io.github.jhcpokemon.democontainer.activity.JsoupDemoActivity;
import io.github.jhcpokemon.democontainer.activity.LoaderDemoActivity;
import io.github.jhcpokemon.democontainer.activity.NotificationDemoActivity;
import io.github.jhcpokemon.democontainer.activity.ProcessStatusDemoActivity;
import io.github.jhcpokemon.democontainer.activity.ProgressBarDemoActivity;
import io.github.jhcpokemon.democontainer.activity.RadioBoxDemoActivity;
import io.github.jhcpokemon.democontainer.activity.ServiceDemo;
import io.github.jhcpokemon.democontainer.activity.ToolbarDemoActivity;
import io.github.jhcpokemon.democontainer.activity.ViewFlipperDemoActivity;
import io.github.jhcpokemon.democontainer.activity.VolleyDemoActivity;

/**
 * The entry of the full demo
 */
public class EntryActivity extends ListActivity implements AdapterView.OnItemClickListener {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<String> list = new ArrayList<>();
        list.add("ProcessStatusDemo");
        list.add("RadioBoxDemo");
        list.add("ProgressBarDemo");
        list.add("HandlerDemo");
        list.add("HttpClientDemo");
        list.add("IntentDemo");
        list.add("BlueToothDemo");
        list.add("ConfAndAsyncDemo");
        list.add("ServiceDemo");
        list.add("BroadcastReceiverDemo");
        list.add("DialogDemo");
        list.add("ContentProviderDemo");
        list.add("DataStorageDemo");
        list.add("AnimationDemo");
        list.add("LoaderDemo");
        list.add("CustomViewDemo");
        list.add("ToolbarDemo");
        list.add("NotificationDemo");
        list.add("JSoupDemo");
        list.add("BitmapResizeDemo");
        list.add("ImageSwitchDemo");
        list.add("ViewFlipperDemo");
        list.add("VolleyDemo");
        list.add("Card&RecyclerViewDemo");
        list.add("BatteryDemo");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(ProcessStatusDemoActivity.class);
                break;
            case 1:
                startActivity(RadioBoxDemoActivity.class);
                break;
            case 2:
                startActivity(ProgressBarDemoActivity.class);
                break;
            case 3:
                startActivity(HandlerDemoActivity.class);
                break;
            case 4:
                startActivity(HttpClientDemoActivity.class);
                break;
            case 5:
                startActivity(IntentDemoActivity.class);
                break;
            case 6:
                startActivity(BlueToothDemoActivity.class);
                break;
            case 7:
                startActivity(ConfigAndSyncDemoActivity.class);
                break;
            case 8:
                startActivity(ServiceDemo.class);
                break;
            case 9:
                startActivity(BroadCastReceiverDemoActivity.class);
                break;
            case 10:
                startActivity(DialogDemoActivity.class);
                break;
            case 11:
                startActivity(ContentProviderDemoActivity.class);
                break;
            case 12:
                startActivity(DataStorageDemoActivity.class);
                break;
            case 13:
                startActivity(AnimationDemoActivity.class);
                break;
            case 14:
                startActivity(LoaderDemoActivity.class);
                break;
            case 15:
                startActivity(CustomViewDemoActivity.class);
                break;
            case 16:
                startActivity(ToolbarDemoActivity.class);
                break;
            case 17:
                startActivity(NotificationDemoActivity.class);
                break;
            case 18:
                startActivity(JsoupDemoActivity.class);
                break;
            case 19:
                startActivity(BitmapResizeDemoActivity.class);
                break;
            case 20:
                startActivity(ImageSwitcherDemoActivity.class);
                break;
            case 21:
                startActivity(ViewFlipperDemoActivity.class);
                break;
            case 22:
                startActivity(VolleyDemoActivity.class);
                break;
            case 23:
                startActivity(CardView_RecyclerViewDemoActivity.class);
                break;
            case 24:
                startActivity(BatteryDemoActivity.class);
            default:
        }
    }

    void startActivity(Class className) {
        intent = new Intent(EntryActivity.this, className);
        startActivity(intent);
    }
}
