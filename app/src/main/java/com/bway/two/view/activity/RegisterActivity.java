package com.bway.two.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bway.two.R;
import com.bway.two.model.base.BaseActivity;
import com.bway.two.model.bean.RegisterBean;
import com.bway.two.presenter.RegisterPresenter;
import com.bway.two.view.IMview.IMLogin;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class RegisterActivity extends BaseActivity implements IMLogin<RegisterBean>{

    private EditText zuce_phone;
    private EditText zhuce_yanzheng;
    private EditText zhuce_pwd;
    private Button zhuce_fasong;
    private Button zhuce_tijiao;
    private CheckBox zhuce_tongyi;
    private RegisterPresenter presenter;
    private  String  url = "http://123.57.33.185:8088/sendCode";
    private  Map<String,Object> map = new HashMap<>();
    private  static final String TAG = "RegisterActivity";
    private int count = 60;
    private  Timer  timer;
    private  Thread  thread;
    private boolean tag = true;
    private  final  static  int MESSAGE = 0;
    private  final  static  int REGISTER = 1;
    private String phone;
    private String pwd;
    private String yanzheng;
    private  String  urlRegsiter = "http://123.57.33.185:8088/user/register";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        zuce_phone = (EditText) findViewById(R.id.zuce_phone);
        zhuce_yanzheng = (EditText) findViewById(R.id.zhuce_yanzheng);
        zhuce_pwd = (EditText) findViewById(R.id.zhuce_pwd);
        zhuce_fasong = (Button) findViewById(R.id.zhuce_fasong);
        zhuce_tijiao = (Button) findViewById(R.id.zhuce_tijiao);
        zhuce_tongyi = (CheckBox) findViewById(R.id.zhuce_tongyi);
    }

    @Override
    public void initData() {
        presenter = new RegisterPresenter(this);
        presenter.onAttach(this);
        timer = new Timer();
       zhuce_fasong.setOnClickListener(new View.OnClickListener() {



           @Override
           public void onClick(View view) {
               phone = zuce_phone.getText().toString().trim();
               map.put("phone", phone);
               map.put("type",1);
               map.put("merchant",0);
               presenter.loadUrlbypost(url,map,MESSAGE);
              changeBtnGetCode();
           }
       });
        zhuce_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yanzheng = zhuce_yanzheng.getText().toString().trim();
                phone = zuce_phone.getText().toString().trim();
                pwd = zhuce_pwd.getText().toString().trim();
                map.put("phone",phone);
                map.put("password",pwd);
                map.put("code",yanzheng);
                map.put("merchant",0);
                presenter.loadUrlbypost(urlRegsiter,map,REGISTER);
                if (zuce_phone.length() != 11) {
                    Toast.makeText(RegisterActivity.this, "手机号位数不够", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phone)&&TextUtils.isEmpty(yanzheng)&&TextUtils.isEmpty(pwd)&&!zhuce_tongyi.isChecked()){
                    Toast.makeText(RegisterActivity.this,"参数不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });

    }


    @Override
    public void onSucceed(RegisterBean registerBean,int code) {
       if (code==MESSAGE){
           if (registerBean.getCode().equals("2006")){
               String code1 = registerBean.getDescirption();
               Toast.makeText(RegisterActivity.this,code1,Toast.LENGTH_SHORT).show();
           }if (registerBean.getCode().equals("-1")){
               Toast.makeText(RegisterActivity.this,"系统处理失败",Toast.LENGTH_SHORT).show();
           }if (registerBean.getCode().equals("2005")){
               Toast.makeText(RegisterActivity.this,"用户未被注册",Toast.LENGTH_SHORT).show();
               changeBtnGetCode();
           }

       }
       if (code==REGISTER){
           if (registerBean.getCode().equals("1000")){
               Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
               finish();
           }else {
               Toast.makeText(RegisterActivity.this,"手机号或验证码错误",Toast.LENGTH_SHORT).show();
           }
       }
    }

    @Override
    public void onError(int code, String err) {

    }

    @Override
    public void checkInfo(boolean isNull, String msg) {

    }
    private void changeBtnGetCode() {
        thread = new Thread() {
            @Override
            public void run() {
                if (tag) {
                    while (count > 0) {
                        count--;
                        if (RegisterActivity.this == null) {
                            break;
                        }

                        RegisterActivity.this
                                .runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        zhuce_fasong.setText("获取验证码("
                                                + count + ")");
                                        zhuce_fasong
                                                .setClickable(false);
                                    }
                                });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    tag = false;
                }
                count = 60;
                tag = true;
                if (RegisterActivity.this != null) {
                    RegisterActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            zhuce_fasong.setText("获取验证码");
                            zhuce_fasong.setClickable(true);
                        }
                    });
                }
            };
        };
        thread.start();
    }
}
