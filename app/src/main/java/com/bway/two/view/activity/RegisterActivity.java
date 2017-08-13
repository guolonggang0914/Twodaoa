package com.bway.two.view.activity;

import android.os.Build;
import android.support.v4.widget.EdgeEffectCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.bway.two.R;
import com.bway.two.model.base.BaseActivity;
import com.bway.two.model.bean.RegisterBean;
import com.bway.two.presenter.RegisterPresenter;
import com.bway.two.view.IMview.IMLogin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends BaseActivity implements IMLogin<RegisterBean> {

    private EditText zuce_phone;
    private EditText zhuce_yanzheng;
    private EditText zhuce_pwd;
    private Button zhuce_fasong;
    private Button zhuce_tijiao;
    private CheckBox zhuce_tongyi;
    private RegisterPresenter presenter;
    private  String phone;
    private List<RegisterBean.ObjectBean>  mList = new ArrayList<>();
    private  String  url = "http://123.57.33.185:8088/sendCode?phone="+phone+"&type=1&merchant=0";
    private  Map<String,Object> map = new HashMap<>();
    private  static final String TAG = "RegisterActivity";
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
        final String phone = zuce_phone.getText().toString().trim();
        String yanzheng = zhuce_yanzheng.getText().toString().trim();
        String pwd = zhuce_pwd.getText().toString().trim();
        presenter.getRegsister(zuce_phone,yanzheng,pwd,zhuce_fasong,zhuce_tijiao,zhuce_tongyi);
       zhuce_yanzheng.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               map.put("phone",phone);
               presenter.loadUrlbypost(url,map);
           }
       });

    }


    @Override
    public void onSucceed(RegisterBean registerBean) {
        RegisterBean.ObjectBean object = registerBean.getObject();
        mList.add(object);
        String code = registerBean.getCode();
        Log.i(TAG, "onSucceed: "+code);
    }

    @Override
    public void onError(int code, String err) {

    }

    @Override
    public void checkInfo(boolean isNull, String msg) {

    }
}
