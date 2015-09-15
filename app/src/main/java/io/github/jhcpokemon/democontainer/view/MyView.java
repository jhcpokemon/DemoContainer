package io.github.jhcpokemon.democontainer.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import io.github.jhcpokemon.democontainer.R;
import io.github.jhcpokemon.democontainer.activity.CustomViewDemoActivity;

/**
 * TODO: document your custom view class.
 */
public class MyView extends View {
    private Paint paint;
    private Rect bounds;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        bounds = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.CYAN);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        paint.setColor(Color.YELLOW);
        paint.setTextSize(getResources().getDimensionPixelSize(R.dimen.my_text));
        String text = getResources().getString(R.string.hello_world);
        paint.getTextBounds(text, 0, text.length(), bounds);
        float textWidth = bounds.width();
        float textHeight = bounds.height();
        canvas.drawText(text, (getWidth() - textWidth) / 2, (getHeight() - textHeight) / 2, paint);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        Log.i(CustomViewDemoActivity.TAG, "View on touch " + event.getAction());
        return super.onTouchEvent(event);
    }
}
