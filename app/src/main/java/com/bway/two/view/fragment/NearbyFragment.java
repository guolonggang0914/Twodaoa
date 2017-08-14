package com.bway.two.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.bway.two.R;
import com.bway.two.model.map.MyLocationListener;
import com.bway.two.utils.ImageShowUtils.YuanjiaoImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
/**
 * autor: 李金涛.
 * date:2017/8/10
 */


public class NearbyFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.map_top)
    MapView mapTop;
    @BindView(R.id.image_ceshi)
    YuanjiaoImageView imageCeshi;
    private View view;
    public LocationClient mLocationClient = null;
    public MyLocationListener myListener = new MyLocationListener();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SDKInitializer.initialize(getActivity().getApplicationContext());
        view = inflater.inflate(R.layout.fragment_nearby, container, false);
        unbinder = ButterKnife.bind(this, view);
        //去掉百度地图的放大缩小图标
        mapTop.showZoomControls(false);
        mLocationClient = new LocationClient(getActivity().getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener( myListener );
        mLocationClient.start();
        //注册监听函数
        return view;
    }

    private void initView() {
        initMap();

    }

    private void initMap() {

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
        if(mapTop!=null){
            mapTop.onDestroy();
        }

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
