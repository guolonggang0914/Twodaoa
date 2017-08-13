package com.bway.two.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bway.two.model.bean.RegisterBean;
import com.bway.two.model.net.okhttp.HttpManager;
import com.bway.two.model.net.okhttp.OkhttpEnginen;
import com.bway.two.view.IMview.IMLogin;
import com.bway.two.view.IMview.customcallback.CallBack;
import com.bway.two.view.IMview.customcallback.EntityCallBack;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 注册逻辑处理类
 * autor: 李金涛.
 * date:2017/8/10
 */


public class RegisterPresenter {
    private Context context;
    private OkhttpEnginen  okhttpEnginen;
    private IMLogin imLogin;
    private Button fs;
    private Timer timer;
    private int count = 60;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            fs.setText("重新发送" +(count));
            fs.setClickable(false);
            if (count<=0){
                fs.setText("获取验证码");
            }else {
                fs.setClickable(true);
            }

        }
    };

    public RegisterPresenter(Context context) {
        this.context = context;
        okhttpEnginen = new OkhttpEnginen();
    }
    //连接
    public void onAttach(IMLogin imLogin){
        this.imLogin = imLogin;
    }
    //解除连接
    public void onDetach(){
        if(imLogin!=null){
            imLogin=null;
        }
    }


    public void getRegsister(final EditText phone, final String yanzheng, final String pwd, final Button fasong, final Button tijiao, final CheckBox tongyi) {
        this.fs = fasong;
        timer = new Timer();
        final String s = phone.getText().toString().trim();
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
               if (phone.length() != 11) {
                   Toast.makeText(context, "手机号位数不够", Toast.LENGTH_SHORT).show();
                   return;
               }
                 if (TextUtils.isEmpty(s)&&TextUtils.isEmpty(yanzheng)&&TextUtils.isEmpty(pwd)&&!tongyi.isChecked()){
                   Toast.makeText(context,"参数不能为空",Toast.LENGTH_SHORT).show();
                   return;
               }
               Toast.makeText(context,"注册成功",Toast.LENGTH_SHORT).show();

           }
       });
    }
    public <T>void loadUrlbypost(String url, Map<String,Object> map){
        HttpManager.getInstance().post(url, map, new EntityCallBack<RegisterBean>() {



            @Override
            public void onSuccess(RegisterBean registerBean) {
                imLogin.onSucceed(registerBean);
            }

            @Override
            public void onFailure(String message, int error) {
                imLogin.onError(error,message);
            }
        });
    }
}
