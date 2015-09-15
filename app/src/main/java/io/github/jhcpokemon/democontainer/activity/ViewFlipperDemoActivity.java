package io.github.jhcpokemon.democontainer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.jhcpokemon.democontainer.R;

public class ViewFlipperDemoActivity extends AppCompatActivity implements View.OnTouchListener{
    @Bind(R.id.view_flipper)
    ViewFlipper viewFlipper;
    private float originX;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper_demo);
        ButterKnife.bind(this);
        viewFlipper.setOnTouchListener(this);
        gestureDetector = new GestureDetector(this, new MyGestureDetectorListener());
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
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
//        if (event.getAction() == KeyEvent.ACTION_DOWN) {
//            originX = event.getX();
//        }
//        if (event.getAction() == KeyEvent.ACTION_UP) {
//            //Drag to right
//            if (event.getX() - originX > 100) {
//                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.right_fade_in));
//                viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.right_fade_out));
//                viewFlipper.showNext();
//                //Drag to left
//            } else if (originX - event.getX() > 100) {
//                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.left_fade_in));
//                viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.left_fade_out));
//                viewFlipper.showPrevious();
//            }
//        }
        gestureDetector.onTouchEvent(event);
        return true;
    }

    private class MyGestureDetectorListener extends SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getAction() == MotionEvent.ACTION_DOWN && e2.getAction() == MotionEvent.ACTION_UP) {
                if (e2.getX() - e1.getX() > 100) {
                    viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_fade_in));
                    viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_fade_out));
                    viewFlipper.showNext();
                } else if (e1.getX() - e2.getX() > 100) {
                    viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_fade_in));
                    viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_fade_out));
                    viewFlipper.showPrevious();
                }
            }
            return true;
        }
    }
}
