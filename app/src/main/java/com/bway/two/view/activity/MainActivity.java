package com.bway.two.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.bway.two.R;
import com.bway.two.model.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {


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
        switch (view.getId()){
            case R.id.min_login:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
