package com.bway.two.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bway.two.R;
import com.bway.two.model.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.frame_content)
    FrameLayout frameContent;
    @BindView(R.id.radio_home)
    RadioButton radioHome;
    @BindView(R.id.radio_nearby)
    RadioButton radioNearby;
    @BindView(R.id.radio_fanli)
    RadioButton radioFanli;
    @BindView(R.id.radio_wode)
    RadioButton radioWode;
    @BindView(R.id.radio_titles)
    RadioGroup radioTitles;
    private TextView min_login;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        min_login = (TextView) findViewById(R.id.min_login);
        min_login.setOnClickListener(this);
    }

    @Override
    public void initData() {


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.min_login:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
