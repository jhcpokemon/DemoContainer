package io.github.jhcpokemon.democontainer.activity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.jhcpokemon.democontainer.R;
import io.github.jhcpokemon.democontainer.util.ResizeImage;

public class BitmapResizeDemoActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    DisplayMetrics metrics = new DisplayMetrics();
    @Bind(R.id.image)
    ImageView imageView;
    @Bind(R.id.text1)
    TextView textView1;
    @Bind(R.id.text2)
    TextView textView2;
    @Bind(R.id.seek1)
    SeekBar seek1;
    @Bind(R.id.seek2)
    SeekBar seek2;
    private double scale;
    private Bitmap bitmap;
    private int originWidth;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_resize);
        ButterKnife.bind(this);
        bitmap = ResizeImage.decodeSampledBitmapFromResource(getResources(), R.drawable.sample, 200, 271);
        originWidth = bitmap.getWidth();
        imageView.setImageResource(R.drawable.sample);
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        scale = bitmap.getHeight() / bitmap.getWidth();
        textView1.setText("宽" + bitmap.getWidth() + ",高" + bitmap.getHeight());
        textView2.setText("旋转 0 度");
        seek1.setMax(metrics.widthPixels - bitmap.getWidth());
        seek1.setOnSeekBarChangeListener(this);
        seek2.setOnSeekBarChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seek1:
                imageView.setLayoutParams(new LinearLayout.LayoutParams(originWidth + progress, (int) ((originWidth + progress) * scale)));
                textView1.setText("宽" + originWidth + progress + ",高" + (int) ((originWidth + progress) * scale));
                break;
            case R.id.seek2:
                Matrix matrix = new Matrix();
                matrix.setRotate(progress);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                imageView.setImageBitmap(bitmap);
                textView2.setText("旋转" + progress + "度");
                break;
            default:
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}

