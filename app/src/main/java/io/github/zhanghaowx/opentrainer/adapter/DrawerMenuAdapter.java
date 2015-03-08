package io.github.zhanghaowx.opentrainer.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import io.github.zhanghaowx.opentrainer.R;
import io.github.zhanghaowx.opentrainer.model.DrawerMenuBean;

/**
 * Adapter to create necessary view object for NavigationDrawerFragment
 */
public class DrawerMenuAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<DrawerMenuBean> mListItemsInDrawerMenu;

    public DrawerMenuAdapter(Context mContext, ArrayList<DrawerMenuBean> listItems) {
        this.mContext = mContext;
        this.mListItemsInDrawerMenu = listItems;
    }

    @Override
    public int getCount() {
        return mListItemsInDrawerMenu.size();
    }

    @Override
    public Object getItem(int position) {
        return mListItemsInDrawerMenu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_drawer_menu_comp, null);

            holder = new ViewHolder();
            holder.mTitle = (TextView) convertView.findViewById(R.id.fragment_drawerMenu_comp_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTitle.setText(mListItemsInDrawerMenu.get(position).getTitle());

        return convertView;
    }

    private static class ViewHolder {
        TextView mTitle;
    }
}