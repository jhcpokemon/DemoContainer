package io.github.jhcpokemon.democontainer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import io.github.jhcpokemon.democontainer.R;
import io.github.jhcpokemon.democontainer.model.User;

/**
 * Created by jhcpokemon on 08/02/15.
 */
public class MyAdapter extends BaseAdapter {
    private List<User> users;
    private Context context;

    public MyAdapter(Context context, List<User> users) {
        this.users = users;
        this.context = context;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return users.get(position).getUserId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.custom_item, null);
            viewHolder = new ViewHolder();
            viewHolder.userId = (TextView) convertView.findViewById(R.id.text_view1);
            viewHolder.userName = (TextView) convertView.findViewById(R.id.text_view2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.userId.setText(users.get(position).getUserId() + "");
        viewHolder.userName.setText(users.get(position).getUserName());
        return convertView;
    }

    static class ViewHolder {
        public TextView userId;
        public TextView userName;
    }
}
