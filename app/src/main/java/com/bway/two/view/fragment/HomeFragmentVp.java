package com.bway.two.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.bway.two.R;
import com.bway.two.model.bean.Foods;
import com.bway.two.view.adapter.HomeGvAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 卢程
 * 2017/8/11.
 */

public class HomeFragmentVp extends Fragment {
    @BindView(R.id.fragment_home_gridview)
    GridView mGridview;
    Unbinder unbinder;
    private String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        url = getArguments().getString("url");
        View view = inflater.inflate(R.layout.fragment_home_vp, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        ArrayList<Foods> list = new ArrayList<>();
        list.add(new Foods("luccc", R.mipmap.food1));
        list.add(new Foods("luccc", R.mipmap.food2));
        list.add(new Foods("luccc", R.mipmap.food3));
        list.add(new Foods("luccc", R.mipmap.food4));
        list.add(new Foods("luccc", R.mipmap.food5));
        list.add(new Foods("luccc", R.mipmap.food6));
        list.add(new Foods("luccc", R.mipmap.food7));
        list.add(new Foods("luccc", R.mipmap.food8));
        list.add(new Foods("luccc", R.mipmap.food9));
        list.add(new Foods("luccc", R.mipmap.food10));
        HomeGvAdapter homeGvAdapter = new HomeGvAdapter(list, getActivity());
        mGridview.setAdapter(homeGvAdapter);
    }

    public static HomeFragmentVp getInstense(String url) {
        HomeFragmentVp homeFragmentVp = new HomeFragmentVp();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        homeFragmentVp.setArguments(bundle);
        return homeFragmentVp;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
