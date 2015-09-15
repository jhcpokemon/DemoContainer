package io.github.jhcpokemon.democontainer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.jhcpokemon.democontainer.R;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Integer> numbers;

    public MyRecyclerViewAdapter(Context context, ArrayList<Integer> numbers) {
        this.context = context;
        this.numbers = numbers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view, null);
        return new ViewHolder(view, new RemoveItemListener() {
            @Override
            public void removeItem() {
//                numbers.remove(i);
//                notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.imageView.setImageResource(numbers.get(i));
        viewHolder.textView.setText("Card"+(i+1));
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }

    interface RemoveItemListener {
        void removeItem();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.item_image)
        ImageView imageView;
        @Bind(R.id.item_text)
        TextView textView;
        private RemoveItemListener listener;

        public ViewHolder(View view, RemoveItemListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            this.listener = listener;
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.removeItem();
        }

        public String getTextViewText() {
            return textView.getText().toString();
        }
    }
}
