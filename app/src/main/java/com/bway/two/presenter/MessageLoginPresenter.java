package com.bway.two.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @类的用途:
 * @姓名: 张惠行
 * @date 2017/8/11
 */

public class MessageLoginPresenter {
    private Context context;
    private int count=60;
    private  Button hq;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            hq.setText("重新发送"+(count));
            hq.setClickable(false);
            if (count==0){
                hq.setText("重新获取");
                hq.setClickable(true);
            }
        }
    };

    public MessageLoginPresenter(Context context) {
        this.context = context;
    }

    public void  getMessageLoginData(final EditText phone, Button huoqu, final String yanzhengma, Button login){
       this.hq=huoqu;
        final String s = phone.getText().toString().trim();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phone.length() != 11) {
                    Toast.makeText(context, "手机号位数不够", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(s)&&TextUtils.isEmpty(yanzhengma)){
                    Toast.makeText(context,"参数不对",Toast.LENGTH_SHORT).show();
                    return;
                }
                    Toast.makeText(context,"登录成功",Toast.LENGTH_SHORT).show();

            }
        });
        final Timer timer = new Timer();
        huoqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (count>0){
                            count--;
                            handler.sendEmptyMessage(0);
                        }
                    }
                },0,1000);
            }
        });

    }
}
