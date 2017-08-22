package com.bway.two.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bway.two.R;
import com.bway.two.model.bean.RecyclerViewItem;
import com.bway.two.view.IMview.customcallback.RebateViewItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/8/12 0012.
 * author  郭龙刚
 */

public class ReBateAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private Context context;
    private List<RecyclerViewItem.ObjectBean>list=new ArrayList<>();
    private int numitem=2;
    private MyItemClickListener mItemClickListener;

    @Override
    public void onClick(View view) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick(view, (Integer) view.getTag());
        }
    }

    //创建一个接口
    public interface MyItemClickListener {
        void onItemClick(View view, int postion);
    }
    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mItemClickListener = listener;
    }
    public ReBateAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.rebatefragment_recyclerview,null);
        view.setOnClickListener(this);
        ViewHolder  vi=new ViewHolder(view);
        return vi;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
         ViewHolder viewHolder= (ViewHolder) holder;
         viewHolder.textView_gradle.setText(list.get(position).getRecordCoding());
         SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
         Date date=new Date(list.get(position).getCashbackSpecificDate());
         String datap = simpleDateFormat.format(date);
         viewHolder.textView_data.setText(datap+"总换");
         Glide.with(context).load(list.get(position).getIntegralStyle()).into(viewHolder.imageView);
         viewHolder.itemView.setTag(position);

    }
    public void setData(List<RecyclerViewItem.ObjectBean> lists)
    {
        list.addAll(lists);
    }

    @Override
    public int getItemCount() {
        if(list.size()<=numitem)
        {
            return  numitem;
        }
        else
        {
            return list.size();
        }
    }
    public void getCount(int numPosition)
    {
        numitem=numPosition;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView,textView_gradle,textView_data;
        private ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.fragment_rebate_prices);
            imageView=itemView.findViewById(R.id.fragment_rebate_image);
            textView_gradle=itemView.findViewById(R.id.fragment_rebate_grade);
            textView_data=itemView.findViewById(R.id.fragment_rebate_date);
        }
    }
}
