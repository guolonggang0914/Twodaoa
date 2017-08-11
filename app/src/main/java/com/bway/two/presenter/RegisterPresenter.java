package com.bway.two.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 注册逻辑处理类
 * autor: 李金涛.
 * date:2017/8/10
 */


public class RegisterPresenter {
    private Context context;
    private Button fs;
    private Timer timer;
    private int count = 60;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            fs.setText("重新发送" +(count));
            if (count==0){
                fs.setText("获取验证码");
            }

        }
    };

    public RegisterPresenter(Context context) {
        this.context = context;
    }


    public void getRegsister(final String phone, final String yanzheng, final String pwd, final Button fasong, final Button tijiao, final CheckBox tongyi) {
        this.fs = fasong;
        timer = new Timer();
        fasong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (count > 0) {
                            count--;
                            handler.sendEmptyMessage(0);
                        }
                    }
                },0, 1000);
            }
        });
       tijiao.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (TextUtils.isEmpty(phone)&&TextUtils.isEmpty(yanzheng)&&TextUtils.isEmpty(pwd)&&!tongyi.isChecked()){
                   Toast.makeText(context,"参数不能为空",Toast.LENGTH_SHORT).show();
               }else {
                   Toast.makeText(context,"注册成功",Toast.LENGTH_SHORT).show();
               }
           }
       });
    }
}
