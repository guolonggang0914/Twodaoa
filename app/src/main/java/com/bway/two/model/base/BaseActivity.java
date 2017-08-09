package com.bway.two.model.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.bway.two.utils.NetUtils.NetWorkUtils;

/**
 * autor: 李金涛.
 * date:2017/8/9
 */


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();//去标题
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        boolean networkConnected = NetWorkUtils.isNetworkConnected(this);
        Log.e("----------------", "onCreate: "+ networkConnected);
        if(networkConnected){
            initView();
            initData();
        }else{
            Toast.makeText(this,"什么鬼，没网了",Toast.LENGTH_SHORT).show();
        }

    }

    public abstract int getLayout();//设置布局view
    public abstract void initView();//初始化view
    public abstract void initData();//初始化数据

}
