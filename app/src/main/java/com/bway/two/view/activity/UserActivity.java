package com.bway.two.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bway.two.R;
import com.bway.two.model.base.BaseActivity;
import com.bway.two.model.bean.FirstEvent;

import org.greenrobot.eventbus.EventBus;

public class UserActivity extends BaseActivity {

    private Button tuichu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_user;
    }

    @Override
    public void initView() {
        tuichu = (Button) findViewById(R.id.tuichu);
    }

    @Override
    public void initData() {
    tuichu.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    });
    }
}
