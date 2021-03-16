package com.codeinger.roomdb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.codeinger.roomdb.R;
import com.codeinger.roomdb.db.enitiy.Category;

import java.util.List;

public class CategorySpnAdapter extends BaseAdapter {

    private final Context context;
    List<Category> list;

    public CategorySpnAdapter(Context context, List<Category> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View item = LayoutInflater.from(context).inflate(R.layout.spn_item, null);
        TextView text = item.findViewById(R.id.text);
        text.setText(list.get(i).getName());
        return item;
    }
}
