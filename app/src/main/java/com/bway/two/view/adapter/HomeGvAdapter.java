package com.bway.two.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.platform.comapi.map.E;
import com.bway.two.R;

import java.util.List;

/**
 * Created by 卢程
 * 2017/8/12.
 */

public class HomeGvAdapter extends BaseAdapter {
    private List<String> mList;
    private Context context;

    public HomeGvAdapter(List<String> list, Context context) {
        mList = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder Viewholder;
        if (convertView == null) {
            Viewholder = new ViewHolder();
            convertView = View.inflate(context, R.layout.fragment_home_gvitem, null);
            Viewholder.image = convertView.findViewById(R.id.gv_image);
            Viewholder.text = convertView.findViewById(R.id.gv_text);
            Viewholder.text.setText(mList.get(position));
            convertView.setTag(Viewholder);
        } else {
            Viewholder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    class ViewHolder {
        ImageView image;
        TextView text;
    }
}
