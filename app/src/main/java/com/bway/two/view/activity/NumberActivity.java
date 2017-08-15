package com.bway.two.view.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bway.two.R;
import com.bway.two.model.base.BaseActivity;
import com.bway.two.model.bean.FirstEvent;
import com.bway.two.model.bean.RegisterBean;
import com.bway.two.presenter.RegisterPresenter;
import com.bway.two.view.IMview.IMLogin;
import com.bway.two.view.IMview.IMLoginTwo;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NumberActivity extends BaseActivity implements View.OnClickListener, IMLogin<RegisterBean>{

    @BindView(R.id.number_fh)
    TextView numberFh;
    @BindView(R.id.number_phone)
    EditText numberPhone;
    @BindView(R.id.number_huoqu)
    Button numberHuoqu;
    @BindView(R.id.number_yanzheng)
    EditText numberYanzheng;
    @BindView(R.id.number_login)
    Button numberLogin;
    @BindView(R.id.number_qita)
    TextView numberQita;
    private int count = 60;
    private Timer timer;
    private  Thread  thread;
    private boolean tag = true;
    private  String  urlLogin = "http://123.57.33.185:8088//user/isExistByPhone";
    private  String  url = "http://123.57.33.185:8088/sendCode";
    private String yanzheng;
    private RegisterPresenter presenter;
    private  final  static  int MESSAGE = 0;
    private  final  static  int REGISTER = 1;
    private Map<String,Object> map = new HashMap<>();
    private String phone;
    private String yanzhengma;


    @Override
    public int getLayout() {
        return R.layout.activity_number;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        numberQita.setOnClickListener(this);
        numberFh.setOnClickListener(this);
        numberHuoqu.setOnClickListener(this);
        numberLogin.setOnClickListener(this);
        presenter = new RegisterPresenter(this);
        presenter.onAttach(this);
        yanzheng = numberYanzheng.getText().toString().trim();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.number_fh:
                finish();
                break;
            case R.id.number_qita:
                startActivity(new Intent(NumberActivity.this,ShoushiPwdActivity.class));

                break;
            case R.id.number_huoqu:
                phone = numberPhone.getText().toString().trim();
                map.put("phone", phone);
                map.put("type",1);
                map.put("merchant",0);
                presenter.loadUrlbypost(url,map,MESSAGE);
                changeBtnGetCode();
                break;
            case R.id.number_login:
                phone = numberPhone.getText().toString().trim();
                yanzhengma = numberYanzheng.getText().toString().trim();
                map.put("phone",phone);
                map.put("code",yanzheng);
                map.put("merchant",0);
                presenter.loadUrlbypost(urlLogin,map,REGISTER);
                if (numberPhone.length() != 11) {
                    Toast.makeText(NumberActivity.this, "手机号位数不够", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phone)&&TextUtils.isEmpty(yanzheng)){
                    Toast.makeText(NumberActivity.this,"参数不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                EventBus.getDefault().post(new FirstEvent(phone));
                finish();
                break;
        }
    }
    private void changeBtnGetCode() {
        thread = new Thread() {
            @Override
            public void run() {
                if (tag) {
                    while (count > 0) {
                        count--;
                        if (NumberActivity.this == null) {
                            break;
                        }

                        NumberActivity.this
                                .runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        numberHuoqu.setText("获取验证码("
                                                + count + ")");
                                        numberHuoqu
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
                if (NumberActivity.this != null) {
                    NumberActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            numberHuoqu.setText("获取验证码");
                            numberHuoqu.setClickable(true);
                        }
                    });
                }
            };
        };
        thread.start();
    }


    @Override
    public void onSucceed(RegisterBean registerBean, int code) {
        if (code==MESSAGE){
            if (registerBean.getCode().equals("2006")){
                String code1 = registerBean.getDescirption();
                Toast.makeText(NumberActivity.this,code1,Toast.LENGTH_SHORT).show();
            }if (registerBean.getCode().equals("-1")){
                Toast.makeText(NumberActivity.this,"系统处理失败",Toast.LENGTH_SHORT).show();
            }if (registerBean.getCode().equals("2005")){
                Toast.makeText(NumberActivity.this,"用户未被注册",Toast.LENGTH_SHORT).show();
                changeBtnGetCode();
            }

        }
        if (code==REGISTER){
            if (registerBean.getCode().equals("1000")){
                Toast.makeText(NumberActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(NumberActivity.this,"手机号或验证码错误",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onError(int code, String err) {

    }

    @Override
    public void checkInfo(boolean isNull, String msg) {

    }
}
