package com.bway.two.view.activity;

import android.os.Build;
import android.support.v4.widget.EdgeEffectCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.bway.two.R;
import com.bway.two.model.base.BaseActivity;
import com.bway.two.presenter.RegisterPresenter;

public class RegisterActivity extends BaseActivity {

    private EditText zuce_phone;
    private EditText zhuce_yanzheng;
    private EditText zhuce_pwd;
    private Button zhuce_fasong;
    private Button zhuce_tijiao;
    private CheckBox zhuce_tongyi;
    private RegisterPresenter presenter;

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
        String phone = zuce_phone.getText().toString().trim();
        String yanzheng = zhuce_yanzheng.getText().toString().trim();
        String pwd = zhuce_pwd.getText().toString().trim();

        presenter.getRegsister(phone,yanzheng,pwd,zhuce_fasong,zhuce_tijiao,zhuce_tongyi);
    }
}
