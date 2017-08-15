package com.bway.two.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bway.two.R;
import com.bway.two.model.base.BaseActivity;

import butterknife.BindView;

public class AnQuanActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.anquan_fh)
    ImageView anquanFh;
    @BindView(R.id.anquan_login_pwd)
    TextView anquanLoginPwd;
    @BindView(R.id.anquan_zf_pwd)
    TextView anquanZfPwd;
    @BindView(R.id.anquan_ss_pwd)
    TextView anquanSsPwd;
    @BindView(R.id.anquan_number_pwd)
    TextView anquanNumberPwd;
    @BindView(R.id.tuichu)
    Button tuichu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_anquan;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
anquanFh.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.anquan_fh:
                finish();
                break;
        }
    }
}
