package io.github.jhcpokemon.democontainer.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import io.github.jhcpokemon.democontainer.view.MyGLSurfaceView;

public class OpenGLDemoActivity extends AppCompatActivity {

    private MyGLSurfaceView surfaceView;
    private MyGestureListener listener;
    private GestureDetector detector;
    private MyGLSurfaceView.MyRender render;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        surfaceView = new MyGLSurfaceView(this);
        setContentView(surfaceView);
        listener = new MyGestureListener();
        detector = new GestureDetector(getApplicationContext(), listener);
        surfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        surfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        surfaceView.onPause();
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            render = surfaceView.getRender();
            if (Math.abs(e1.getX() - e2.getX()) > 5 && Math.abs(e1.getY() - e2.getY()) < 20) {
                render.reDraw(e2.getX() - e1.getX(), 0);
            } else if (Math.abs(e1.getX() - e2.getX()) < 20 && Math.abs(e1.getY() - e2.getY()) > 5) {
                render.reDraw(e2.getY() - e1.getY(), 1);
            }
            return true;
        }
    }
}

