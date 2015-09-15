package io.github.jhcpokemon.democontainer.listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private Context context;
    private OnItemClickListener listener;
    private GestureDetector gestureDetector;
    private RecyclerView recyclerView;

    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.recyclerView = recyclerView;
        gestureDetector = new GestureDetector(context, new MyGestureListener());
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && listener != null && gestureDetector.onTouchEvent(e)) {
            listener.onItemClick(childView, rv.getChildLayoutPosition(childView));
            rv.scrollToPosition(0);
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetector = new GestureDetector(context, new MyGestureListener());
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
            try {
                listener.onItemLongClick(childView, recyclerView.getChildAdapterPosition(childView));
            } catch (NullPointerException exception) {
                exception.getMessage();
            }
        }
    }
}
