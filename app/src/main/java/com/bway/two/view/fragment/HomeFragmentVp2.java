package com.bway.two.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bway.two.R;
import com.bway.two.model.bean.RcData;
import com.bway.two.view.adapter.HomeRcAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 卢程
 * 2017/8/11.
 */

public class HomeFragmentVp2 extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.home_recycler_view)
    RecyclerView mRecyclerView;
    private String url;
    private List<RcData> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        url = getArguments().getString("url");
        View view = inflater.inflate(R.layout.fragment_home_vp2, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mList = new ArrayList<>();
        initData();
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            mList.add(new RcData());
        }
        HomeRcAdapter adapter = new HomeRcAdapter(getContext(), mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
    }

    public static HomeFragmentVp2 getInstense(String url) {
        HomeFragmentVp2 homeFragmentVp = new HomeFragmentVp2();
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
