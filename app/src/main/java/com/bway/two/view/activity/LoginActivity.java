package com.bway.two.view.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bway.two.R;
import com.bway.two.model.base.BaseActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private TextView login_other;
    private TextView login_zhuce;
    private Dialog alertDialog;
    private ImageView quxiao;
    private Button login_weibo;
    private Button login_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        getDialog();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        login_other = (TextView) findViewById(R.id.login_other);
        login_zhuce = (TextView) findViewById(R.id.login_zhuce);
        quxiao = (ImageView) findViewById(R.id.quxiao);
        login_weibo = (Button) findViewById(R.id.login_weibo);
        login_phone = (Button) findViewById(R.id.login_phone);

    }

    @Override
    public void initData() {
        login_other.setOnClickListener(this);
        login_zhuce.setOnClickListener(this);
        quxiao.setOnClickListener(this);
        login_weibo.setOnClickListener(this);
        login_phone.setOnClickListener(this);
    }

    private void getDialog() {
        alertDialog = new Dialog(LoginActivity.this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        final View view = LayoutInflater.from(LoginActivity.this).inflate(R.layout.dialog_min,null);
        RadioButton min_qq = view.findViewById(R.id.min_qq);
        RadioButton  min_weixin = view.findViewById(R.id.min_weixin);
        RadioButton min_zhanghao = view.findViewById(R.id.min_zhanghao);
        alertDialog.setCanceledOnTouchOutside(false);
        Window dialogWindow = alertDialog.getWindow();
        dialogWindow.setGravity(Gravity.HORIZONTAL_GRAVITY_MASK | Gravity.BOTTOM);
        view.findViewById(R.id.min_quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           alertDialog.dismiss();
            }
        });
        alertDialog.setContentView(view);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.login_other:
                alertDialog.show();
                break;
            case  R.id.login_zhuce:
                Intent  intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case  R.id.quxiao:
                finish();
                break;
            case  R.id.login_phone:

                break;
            case  R.id.login_weibo:

                break;
        }
    }
}
