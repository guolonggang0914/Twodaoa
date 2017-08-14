package com.bway.two.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bway.two.R;
import com.bway.two.view.adapter.HomeVpAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 卢程
 * 2017/8/10.
 */

public class HomeFragment extends Fragment {

    @BindView(R.id.fragment_home_search)
    LinearLayout mSearch;
    @BindView(R.id.fragment_home_xiaoxi)
    LinearLayout mXiaoxi;
    @BindView(R.id.fragment_home_saosao)
    LinearLayout mSaosao;
    @BindView(R.id.fragment_home_banner)
    Banner mBanner;
    @BindView(R.id.fragment_home_viewpager)
    ViewPager mViewpager;
    @BindView(R.id.fragment_home_fuli)
    LinearLayout mFuli;
    @BindView(R.id.fragment_home_dadao)
    LinearLayout mDadao;
    @BindView(R.id.nearby_map)
    ImageView nearbyMap;
    @BindView(R.id.fragment_home_viewpager2)
    ViewPager mViewpager2;
    Unbinder unbinder;
    @BindView(R.id.fragment_home_tablayout)
    TabLayout mTablayout;
    private List<String> images;
    private List<Fragment> fragments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        loadBanner();
//        loadViewpager();
        loadTabView();
    }

    private void loadTabView() {
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getChildFragmentManager());
        mViewpager2.setAdapter(adapter);

        //TabLayout
        mTablayout.setTabMode(TabLayout.MODE_FIXED);
        mTablayout.setupWithViewPager(mViewpager2);
    }

    private void loadViewpager() {
        fragments.add(new HomeFragmentVp());
        fragments.add(new HomeFragmentVp());
        HomeVpAdapter vpAdapter = new HomeVpAdapter(getActivity().getSupportFragmentManager(), fragments);
        mViewpager.setAdapter(vpAdapter);
    }

    private void initView() {
        images = new ArrayList<>();
        fragments = new ArrayList<>();
    }

    private void loadBanner() {

        images.add("http://img1.yulin520.com/news/VQA5D2ZGFFR0O5E1JWUK.jpg#488_597");
        images.add("http://img1.yulin520.com/news/QQ7914TLXFR0OMOU5OW6.jpg#628_640");
        images.add("http://img1.yulin520.com/news/ZQ8P3878YFR0OMXSC079.jpg#723_599");
        images.add("http://www.yulin520.com/a2a/h5/impress/i1/348?a=1502452006447");

        mBanner.setImageLoader(new GlideImagerloader());
        mBanner.setImages(images);
        mBanner.setBannerTitles(images);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mBanner.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.fragment_home_search,
            R.id.fragment_home_xiaoxi,
            R.id.fragment_home_saosao,
            R.id.fragment_home_fuli,
            R.id.fragment_home_dadao,
            R.id.nearby_map})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_home_search:
                break;
            case R.id.fragment_home_xiaoxi:
                break;
            case R.id.fragment_home_saosao:
                break;
            case R.id.fragment_home_fuli:
                break;
            case R.id.fragment_home_dadao:
                break;
            case R.id.nearby_map:
                break;
        }
    }

    class GlideImagerloader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        public final int COUNT = 5;
        private String[] titles = new String[]{"美食", "休闲娱乐", "生活服务", "酒店", "全部"};

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            fragments.add(HomeFragmentVp.getInstense("a"));
            fragments.add(HomeFragmentVp.getInstense("b"));
            fragments.add(HomeFragmentVp.getInstense("c"));
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
