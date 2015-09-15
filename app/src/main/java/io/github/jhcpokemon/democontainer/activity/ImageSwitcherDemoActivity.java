package io.github.jhcpokemon.democontainer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.jhcpokemon.democontainer.R;

public class ImageSwitcherDemoActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory, View.OnClickListener {
    @Bind(R.id.image_switcher)
    ImageSwitcher imageSwitcher;
    @Bind(R.id.linear_in_scroller)
    LinearLayout linearInScroller;
    private Integer[] largePicResId;
    private Integer[] smallPicResID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_switcher_demo);
        ButterKnife.bind(this);
        largePicResId = new Integer[]{R.drawable.img_1_large,R.drawable.img_2_large};
        smallPicResID = new Integer[]{R.drawable.img_1_small,R.drawable.img_2_small};
        imageSwitcher.setInAnimation(this, android.R.anim.fade_in);
        imageSwitcher.setOutAnimation(this,android.R.anim.fade_in);
        imageSwitcher.setFactory(this);
        imageSwitcher.setImageResource(largePicResId[0]);
        for (int i = 0;i < largePicResId.length;i ++){
            linearInScroller.addView(getImageView(i));
        }
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

    ImageView getImageView(int i){
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(smallPicResID[i]);
        imageView.setId(i);
        imageView.setOnClickListener(this);
        return imageView;
    }

    @Override
    public View makeView() {
        return new ImageView(this);
    }

    @Override
    public void onClick(View v) {
        imageSwitcher.setImageResource(largePicResId[v.getId()]);
    }
}
