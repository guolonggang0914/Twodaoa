package com.bway.two.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bway.two.R;
import com.bway.two.model.bean.NearbyMessage;

import java.util.List;

/**
 * 附近界面中viewpage中的信息
 * autor: 李金涛.
 * date:2017/8/14
 */


public class ContentItemdapter extends RecyclerView.Adapter<ContentItemdapter.ViewHoler> {

    private Context context;
    private List<NearbyMessage> messageList ;

    public ContentItemdapter(Context context, List<NearbyMessage> messageList) {
        this.context = context;
        this.messageList = messageList;
    }

    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.nearby_meishi_item, null);
        ViewHoler viewHoler = new ViewHoler(view);
        return viewHoler;
    }

    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {
        NearbyMessage nearbyMessage = messageList.get(position);
        String jifen = "积分率 " + nearbyMessage.getJifen() + "%";
        SpannableString spannableString = new SpannableString(jifen);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
        spannableString.setSpan(colorSpan, 3, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);



        holder.jifen.setText(spannableString);
        holder.juli.setText("安贞距您<"+nearbyMessage.getJuli());
        holder.money.setText("￥"+nearbyMessage.getMoney()+"/人");
        holder.name.setText(nearbyMessage.getName());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    class ViewHoler extends RecyclerView.ViewHolder{

        TextView jifen,juli,money,name;
        public ViewHoler(View itemView) {
            super(itemView);
            jifen = itemView.findViewById(R.id.txt_jifen);
            juli =  itemView.findViewById(R.id.txt_juli);
            money = itemView.findViewById(R.id.txt_money);
            name = itemView.findViewById(R.id.txt_meishi_name);
        }
    }
}
