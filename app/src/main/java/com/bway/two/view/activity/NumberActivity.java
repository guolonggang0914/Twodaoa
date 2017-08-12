package com.bway.two.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bway.two.R;
import com.bway.two.model.base.BaseActivity;
import com.bway.two.presenter.MessageLoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NumberActivity extends BaseActivity implements View.OnClickListener {

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


    private MessageLoginPresenter presenter;


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
        presenter = new MessageLoginPresenter(this);
        String yanzheng = numberYanzheng.getText().toString().trim();
        presenter.getMessageLoginData(numberPhone, numberHuoqu, yanzheng, numberLogin);
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
        }
    }

}
