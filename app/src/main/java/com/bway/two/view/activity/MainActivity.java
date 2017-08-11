package com.bway.two.view.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bway.two.R;
import com.bway.two.model.base.BaseActivity;
import com.bway.two.view.fragment.HomeFragment;
import com.bway.two.view.fragment.MineFragment;
import com.bway.two.view.fragment.NearbyFragment;
import com.bway.two.view.fragment.RebateFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {


    @BindView(R.id.frame_content)
    FrameLayout frameContent;
    @BindView(R.id.radio_home)
    RadioButton radioHome;
    @BindView(R.id.radio_nearby)
    RadioButton radioNearby;
    @BindView(R.id.radio_fanli)
    RadioButton radioFanli;
    @BindView(R.id.radio_wode)
    RadioButton radioWode;
    @BindView(R.id.radio_titles)
    RadioGroup radioTitles;
    private List<Fragment> fragments = new ArrayList<>();
    private FragmentManager fm;
    private FragmentTransaction ft;
//    private RadioGroup radioTitles;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
//        radioTitles = (RadioGroup) findViewById(R.id.radio_titles);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {

        HomeFragment homeFragment = new HomeFragment();
        NearbyFragment nearbyFragment = new NearbyFragment();
        RebateFragment rebateFragment = new RebateFragment();
        MineFragment mineFragment = new MineFragment();
        fragments.add(homeFragment);
        fragments.add(nearbyFragment);
        fragments.add(rebateFragment);
        fragments.add(mineFragment);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            ft.add(R.id.frame_content, fragments.get(i));
            if (i == 0) {
                ft.show(fragments.get(i));
            } else {
                ft.hide(fragments.get(i));
            }
        }
        ft.commit();
        radioTitles.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                RadioButton rbtn = (RadioButton) findViewById(i);
                int tag = Integer.parseInt(rbtn.getTag().toString());
                ft = fm.beginTransaction();
                for (int j = 0; j < fragments.size(); j++) {
                    if (j == tag) {
                        ft.show(fragments.get(j));
                    } else {
                        ft.hide(fragments.get(j));
                    }
                }
                ft.commit();
            }
        });

    }

}
