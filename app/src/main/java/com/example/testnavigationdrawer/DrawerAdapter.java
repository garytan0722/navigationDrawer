package com.example.testnavigationdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by eric on 2015/2/10.
 */
public class DrawerAdapter extends ArrayAdapter<DrawerEntryItem> {

    private static class ViewHolder {
        TextView drawerTitle;
    }

    private final int mItemLayoutId;
    private final LayoutInflater mLayoutInflater;
    private final List<DrawerEntryItem> mDrawerList;

    public DrawerAdapter(Context context, int resource, List<DrawerEntryItem> drawerList) {
        super(context, resource, drawerList);
        mItemLayoutId = resource;
        mLayoutInflater = LayoutInflater.from(context);
        mDrawerList = drawerList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(mItemLayoutId, parent, false);
            vh = new ViewHolder();
            vh.drawerTitle = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.drawerTitle.setText(((DrawerEntryItem) mDrawerList.get(position)).getTitle());
        return convertView;
    }
}

