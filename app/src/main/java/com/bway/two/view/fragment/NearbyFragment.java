package com.bway.two.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.bway.two.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * autor: 李金涛.
 * date:2017/8/10
 */


public class NearbyFragment extends Fragment {

    @BindView(R.id.map_top)
    MapView mapTop;
    Unbinder unbinder;
    private View view;

    String url = "https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=92e00c9b8f5494ee8722081f15ce87c3/29381f30e924b899c83ff41c6d061d950a7bf697.jpg";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SDKInitializer.initialize(getActivity().getApplicationContext());
        view = inflater.inflate(R.layout.fragment_nearby, container, false);
        unbinder = ButterKnife.bind(this, view);
//        YuanjiaoImageView imageView = view.findViewById(R.id.image_ceshi);
//        Glide.with(getActivity()).load(url).into(imageView);
//        ImageUtils.newInstance().disPlay(getActivity(),url,imageView);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapTop.onDestroy();
    }
    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapTop.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapTop.onPause();
    }
}
