package io.github.zhanghaowx.opencourse.adapter.core;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import io.github.zhanghaowx.opencourse.R;
import io.github.zhanghaowx.opencourse.model.core.DrawerMenuBean;

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
        MenuItemViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.fragment_drawer_menu_item, null);

            holder = new MenuItemViewHolder();
            holder.mTitle = (TextView) convertView.findViewById(R.id.fragment_drawerMenu_item_title);
            holder.mIconView = (ImageView) convertView.findViewById(R.id.fragment_drawerMenu_icon);

            convertView.setTag(holder);
        } else {
            holder = (MenuItemViewHolder) convertView.getTag();
        }

        holder.mTitle.setText(mListItemsInDrawerMenu.get(position).getTitle());
        holder.mIconView.setImageResource(mListItemsInDrawerMenu.get(position).getIconResourceId());

        return convertView;
    }

    private static class MenuItemViewHolder {
        ImageView mIconView;
        TextView mTitle;
    }
}