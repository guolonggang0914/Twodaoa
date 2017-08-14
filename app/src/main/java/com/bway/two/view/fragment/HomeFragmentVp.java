package com.bway.two.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bway.two.R;
import com.bway.two.model.bean.HomeMessage;

import java.util.ArrayList;

/**
 * Created by 卢程
 * 2017/8/11.
 */

public class HomeFragmentVp extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        String url = getArguments().getString("url");
        return inflater.inflate(R.layout.fragment_home_vp, null);
    }

    public static HomeFragmentVp getInstense(String url){
        HomeFragmentVp homeFragmentVp = new HomeFragmentVp();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        return homeFragmentVp;
    }
}
