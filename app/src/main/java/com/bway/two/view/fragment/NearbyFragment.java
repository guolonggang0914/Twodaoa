package com.bway.two.view.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.bway.two.R;
import com.bway.two.view.adapter.ContentPageAdapter;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.fragment_home_tablayout)
    TabLayout fragmentHomeTablayout;
    @BindView(R.id.fragment_home_viewpager2)
    ViewPager fragmentHomeViewpager2;
    @BindView(R.id.nestedScrollView)
    NestedScrollView mNestedScrollView;
 /*   @BindView(R.id.image_dingwei)
    MyImageView imageDingwei;*/

    private View view;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private ContentPageAdapter mAdapter;
    private boolean isFirstLocation = true;
    private LocationManager locationManager;
    private String provider;
    BaiduMap baiduMap;
    boolean ifFrist = true;
    String name;
    private double lat;
    private double lon;

    public LocationClient mLocationClient=null;
    public MyLocationListener mMyLocationListener=null;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_nearby, container, false);
        unbinder = ButterKnife.bind(this, view);
        //去掉百度地图的放大缩小图标
        mapTop.showZoomControls(false);


        initView();
        return view;
    }

    private void initView() {
        initMap();
        initTablayout();
    }

    private void initTablayout() {
        titles.add("美食");
        titles.add("休闲娱乐");
        titles.add("生活服务");
        titles.add("酒店");
        titles.add("全部");
        fragmentHomeTablayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        for (int i = 0; i < titles.size(); i++) {
            ContentFragment fr = ContentFragment.getInstense("hah");
            fragments.add(fr);
            fragmentHomeTablayout.addTab(fragmentHomeTablayout.newTab().setText(titles.get(i)));//添加tab选项卡
        }
        mNestedScrollView.setFillViewport(true);
        fragmentHomeTablayout.setupWithViewPager(fragmentHomeViewpager2);//将TabLayout和ViewPager关联起来。
        mAdapter = new ContentPageAdapter(getChildFragmentManager(), fragments, titles);
        fragmentHomeViewpager2.setAdapter(mAdapter);//给ViewPager设置适配器
    }

    private void initMap() {

     /*   Intent intent=new Intent("com.baidu.location.f");
        getActivity().startService(intent);*/
        Log.i("1", "1");
        mMyLocationListener=new MyLocationListener();
        location(mMyLocationListener);

        // 获取baiduMap对象
        baiduMap = mapTop.getMap();
        // 设置可改变地图位置
        baiduMap.setMyLocationEnabled(true);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        List<String> list = locationManager.getProviders(true);
        if (list.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (list.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;

        } else {
            Toast.makeText(getActivity(), "当前不能提供位置信息", Toast.LENGTH_LONG).show();
            return;
        }
      /*  Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            navigateTo(location);
        }*/


    }

    private void navigateTo(Location location) {
        // 按照经纬度确定地图位置
        if (ifFrist) {
            LatLng ll = new LatLng(location.getLatitude(),
                    location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            // 移动到某经纬度
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomBy(5f);
            // 放大
            baiduMap.animateMapStatus(update);

            ifFrist = false;
        }
        // 显示个人位置图标
        MyLocationData.Builder builder = new MyLocationData.Builder();
        builder.latitude(location.getLatitude());
        builder.longitude(location.getLongitude());
        MyLocationData data = builder.build();
        baiduMap.setMyLocationData(data);
    }

    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderDisabled(String arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onLocationChanged(Location arg0) {
            // TODO Auto-generated method stub
            // 位置改变则重新定位并显示地图
            navigateTo(arg0);
        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        if (mapTop != null) {
            mapTop.onDestroy();
        }
        if (locationManager != null) {
            locationManager.removeUpdates(locationListener);
        }
        baiduMap.setMyLocationEnabled(false);
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


    /**
     * 添加marker
     */
    private void setMarker() {
        Log.v("pcw","setMarker : lat : "+ lat+" lon : " + lon);
        //定义Maker坐标点
        LatLng point = new LatLng(lat, lon);
        //生成一个TextView用户在地图中显示InfoWindow
//        RadioButton checkBox = new RadioButton(getActivity().getApplicationContext());
//        checkBox.setButtonDrawable(null);
//        checkBox.setCompoundDrawables(null, R.drawable.fujindingwei, null, null);
//        Bitmap viewBitmap = getViewBitmap(location);
        //构建Marker图标
        //调用百度地图提供的api获取刚转换的Bitmap
//        BitmapDescriptor bitmapDescriptor=BitmapDescriptorFactory.fromBitmap(viewBitmap);
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.fujindingwei);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);

        //在地图上添加Marker，并显示
        baiduMap.addOverlay(option);
    }
    /**
     *把View转换成Bitmap类型
     *@paramaddViewContent要转换的View
     *@return
     */
    public static Bitmap getViewBitmap(View addViewContent) {
        addViewContent.setDrawingCacheEnabled(true);
        addViewContent.measure(
                View.MeasureSpec.makeMeasureSpec(0,
                        View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0,
                        View.MeasureSpec.UNSPECIFIED));
        addViewContent.layout(0,0,
                addViewContent.getMeasuredWidth(),
                addViewContent.getMeasuredHeight());


        addViewContent.buildDrawingCache();
        Bitmap cacheBitmap = addViewContent.getDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
        return bitmap;
    }
    /**
     * 设置中心点
     */
    private void setUserMapCenter() {
        Log.v("pcw","setUserMapCenter : lat : "+ lat+" lon : " + lon);
        LatLng cenpt = new LatLng(lat,lon);
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(18)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        baiduMap.setMapStatus(mMapStatusUpdate);

    }
    /**
     * 实现定位监听 位置一旦有所改变就会调用这个方法
     * 可以在这个方法里面获取到定位之后获取到的一系列数据
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());

            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            lat = location.getLatitude();
            lon = location.getLongitude();
            name = location.getAddrStr();
            //这个判断是为了防止每次定位都重新设置中心点和marker
            if(isFirstLocation){
                isFirstLocation = false;
                setMarker();
                setUserMapCenter();
            }
            Log.v("pcw","lat : " + lat+" lon : " + lon);
            Log.i("BaiduLocationApiDem", sb.toString());
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }

    }
    public void location(BDLocationListener listener) {
        mLocationClient = new LocationClient(getActivity().getApplicationContext());

        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);// 中文地址
        option.setCoorType("bd09ll");// gcj02 国测局经纬度坐标系 ；bd09 百度墨卡托坐标系；bd09ll
        // 百度经纬度坐标系
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);// 设置定位模式
        option.setScanSpan(5*60000);//检查周期 小于1秒的按1秒
        mLocationClient.setLocOption(option);
        Log.i("2", "2");
        mLocationClient.registerLocationListener(listener);
        mLocationClient.start();
    }
    /**
     * 用这个方法的时候要注意ct是 调用这个函数类的context，方法的参数应该是this.getApplication传到这个方法里面去，而不是这个类的context，一定要注意。
     * @param ct
     */
    public void location(Context ct) {
        mLocationClient = new LocationClient(ct);
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);// 中文地址
        option.setCoorType("bd09ll");// gcj02 国测局经纬度坐标系 ；bd09 百度墨卡托坐标系；bd09ll
        // 百度经纬度坐标系
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);// 设置定位模式
        option.setScanSpan(5 * 60000);//检查周期 小于1秒的按1秒
        mLocationClient.setLocOption(option);
        Log.i("2", "2");
        mLocationClient.registerLocationListener(new MyLocationListener());
        mLocationClient.start();
    }
}
