package io.github.jhcpokemon.democontainer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import io.github.jhcpokemon.democontainer.R;

public class AnimationDemoActivity extends AppCompatActivity {

    private ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_demo);
        pic = (ImageView) findViewById(R.id.pic);
        Button alphaBtn = (Button) findViewById(R.id.button1);
        alphaBtn.setOnClickListener(new MyAnimationListener(AnimationType.Alpha));
        Button rotateBtn = (Button) findViewById(R.id.button2);
        rotateBtn.setOnClickListener(new MyAnimationListener(AnimationType.Rotate));
        Button scaleBtn = (Button) findViewById(R.id.button3);
        scaleBtn.setOnClickListener(new MyAnimationListener(AnimationType.Scale));
        Button translateBtn = (Button) findViewById(R.id.button4);
        translateBtn.setOnClickListener(new MyAnimationListener(AnimationType.Translate));
        Button complexBtn = (Button) findViewById(R.id.button5);
        complexBtn.setOnClickListener(new MyAnimationListener(AnimationType.Complex));
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

    enum AnimationType {
        Alpha, Rotate, Scale, Translate, Complex
    }

    class MyAnimationListener implements View.OnClickListener {
        private AnimationType animationType;

        public MyAnimationListener(AnimationType type) {
            animationType = type;
        }

        @Override
        public void onClick(View v) {
            switch (animationType) {
                case Alpha:
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f, 0.8f);
                    alphaAnimation.setDuration(3000);
                    pic.startAnimation(alphaAnimation);
                    break;
                case Rotate:
                    RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(3000);
                    pic.startAnimation(rotateAnimation);
                    break;
                case Scale:
                    ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1.2f, 1f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    scaleAnimation.setDuration(3000);
                    pic.startAnimation(scaleAnimation);
                    break;
                case Translate:
                    TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f);
                    translateAnimation.setDuration(3000);
                    pic.bringToFront();
                    pic.startAnimation(translateAnimation);
                    break;
                case Complex:
                    AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.2f, 0.8f);
                    alphaAnimation1.setDuration(3000);
                    RotateAnimation rotateAnimation1 = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation1.setDuration(3000);
                    AnimationSet animationSet = new AnimationSet(false);
                    animationSet.addAnimation(alphaAnimation1);
                    animationSet.addAnimation(rotateAnimation1);
                    pic.startAnimation(animationSet);
                default:
                    break;
            }
        }
    }
}
