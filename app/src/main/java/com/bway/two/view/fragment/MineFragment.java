package com.bway.two.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bway.two.R;
import com.bway.two.model.bean.FirstEvent;
import com.bway.two.view.activity.GuanYuActivity;
import com.bway.two.view.activity.LoginActivity;
import com.bway.two.view.activity.AnQuanActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @类的用途:
 * @姓名: 张惠行
 * @date 2017/8/10
 */

public class MineFragment extends Fragment implements View.OnClickListener {

    private TextView min_login;
    private LinearLayout min_ll;
    private TextView username;
    private TextView guanyu;
    private TextView anquan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        return inflater.inflate(R.layout.fragmet_min,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        min_login = getView().findViewById(R.id.min_login);
        min_ll = getView().findViewById(R.id.min_llusername);
        username = getView().findViewById(R.id.min_username);
        guanyu = getView().findViewById(R.id.min_guanyu);
        anquan = getView().findViewById(R.id.min_anquan);
        username.setOnClickListener(this);
        min_login.setOnClickListener(this);
        guanyu.setOnClickListener(this);
        anquan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.min_login:
                Intent  intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.min_anquan:
                startActivity(new Intent(getActivity(), AnQuanActivity.class));
                break;
            case R.id.min_guanyu:
                startActivity(new Intent(getActivity(), GuanYuActivity.class));
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
               if (event.getMsg() != null){
                     min_login.setVisibility(View.GONE);
                    min_ll.setVisibility(View.VISIBLE);
                   username.setText(event.getMsg());
        }
             }
    @Override
    public void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }
}
