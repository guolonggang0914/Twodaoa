package com.bway.two.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bway.two.R;
import com.bway.two.model.base.BaseActivity;
import com.bway.two.model.bean.City;
import com.bway.two.model.bean.CityData;
import com.bway.two.model.net.okhttp.HttpManager;
import com.bway.two.utils.ImageShowUtils.SlideBar;
import com.bway.two.utils.common.AddressUtil;
import com.bway.two.view.IMview.customcallback.EntityCallBack;
import com.bway.two.view.adapter.CityFlowAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class CityCheckActivity extends BaseActivity {

    @BindView(R.id.slv_address)
    StickyListHeadersListView slvAddress;
    @BindView(R.id.sb_right)
    SlideBar sbRight;
    @BindView(R.id.txt_letter)
    TextView txtLetter;
    @BindView(R.id.home_city_back)
    ImageView homeCityBack;
    private String url = "http://123.57.33.185:8088/findAllCityList";
    private List<CityData> cityList;
    private CityFlowAdapter cityFlowAdapter;


    @Override
    public int getLayout() {
        return R.layout.activity_city_check;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        sbRight.setText(txtLetter);
        cityList = new ArrayList<>();

        HttpManager.getInstance().post(url, new HashMap<String, Object>(), new EntityCallBack<City>() {
            @Override
            public void onSuccess(City o) {
                List<City.ObjectBean> object = o.getObject();
                for (int i = 0; i < object.size(); i++) {
                    CityData cityData = new CityData();
                    cityData.setAreaname(object.get(i).getAreaname());
                    cityData.setShortname(object.get(i).getShortname());
                    cityData.setId(object.get(i).getId());
                    cityData.setLat(object.get(i).getLat());
                    cityData.setLng(object.get(i).getLng());
                    cityList.add(cityData);
                }
                Collections.sort(cityList, new AddressUtil.AddressCompartor());

                cityFlowAdapter = new CityFlowAdapter(CityCheckActivity.this, cityList);
                cityFlowAdapter.setIndex();
                slvAddress.setAdapter(cityFlowAdapter);
                slvAddress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = getIntent();
                        intent.putExtra("city", cityList.get(i).getAreaname());
                        CityCheckActivity.this.setResult(366, intent);
                        finish();
                    }
                });

            }

            @Override
            public void onFailure(String message, int error) {

            }
        });


        // 给SlideBar设置触摸监听事件

        sbRight.setListener(new SlideBar.OnTouchDownListener() {
            @Override
            public void onTouch(String letter) {
                int index = cityFlowAdapter.getIndex(letter);
                if (index != -1) {
                    // listview设置当前的位置
                    slvAddress.setSelection(index);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.home_city_back)
    public void onViewClicked() {
        finish();
    }
}
