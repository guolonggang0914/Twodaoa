package com.bway.two.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bway.two.R;
import com.bway.two.model.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhangHaoLoginActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.zhanghao_fh)
    ImageView zhanghaoFh;
    @BindView(R.id.zhanghao_phone)
    EditText zhanghaoPhone;
    @BindView(R.id.zhanghao_pwd)
    EditText zhanghaoPwd;
    @BindView(R.id.zhanghao_login)
    Button zhanghaoLogin;
    @BindView(R.id.zhanghao_wangjipwd)
    TextView zhanghaoWangjipwd;

    @Override
    public int getLayout() {
        return R.layout.activity_zhang_hao_login;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
         zhanghaoFh.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.zhanghao_fh:
                finish();
                break;
        }
    }
}
