package com.bway.two.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bway.two.R;
import com.bway.two.model.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    private TextView login_other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        login_other = (TextView) findViewById(R.id.login_other);
    }

    @Override
    public void initData() {

    }
}
