package com.bway.two.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bway.two.R;
import com.bway.two.model.bean.NearbyMessage;
import com.bway.two.view.adapter.ContentItemdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 卢程
 * 2017/8/11.
 */

public class ContentFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.home_recycler_view)
    RecyclerView mRecyclerView;
    private String url;

    private List<NearbyMessage> messageList = new ArrayList<>();
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
        initData();
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            messageList.add(new NearbyMessage("小可爱西点",45+i,25,100));
        }
        ContentItemdapter adapter = new ContentItemdapter(getActivity(),messageList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(adapter);

    }

    public static ContentFragment getInstense(String url) {
        ContentFragment homeFragmentVp = new ContentFragment();
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
