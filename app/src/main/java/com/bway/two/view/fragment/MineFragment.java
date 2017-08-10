package com.bway.two.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bway.two.R;
import com.bway.two.model.net.okhttp.HttpManager;
import com.bway.two.view.IMview.customcallback.EntityCallBack;
import com.bway.two.view.activity.LoginActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * @类的用途:
 * @姓名: 张惠行
 * @date 2017/8/10
 */

public class MineFragment extends Fragment implements View.OnClickListener {

    private TextView min_login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.min_layout,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        min_login = getView().findViewById(R.id.min_login);
        min_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.min_login:
                Intent  intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
