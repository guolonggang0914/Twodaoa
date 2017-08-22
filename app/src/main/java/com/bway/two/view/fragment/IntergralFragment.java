package com.bway.two.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bway.two.R;
import com.bway.two.model.bean.RebateScoreBean;
import com.bway.two.presenter.RebateScorePresenter;
import com.bway.two.view.IMview.customcallback.RebateScore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

public class IntergralFragment extends Fragment implements RebateScore<RebateScoreBean>{
    private String url="http://123.57.33.185:8088/user/intergral/records";
    private RebateScorePresenter rebateScore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_integerral,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDataFromServer();
    }

    private void getDataFromServer() {
        rebateScore = new RebateScorePresenter(this);
        Map<String,Object> map=new HashMap<>();
        map.put("token","faf9105720d000f7bcea972fabb4b518");
        rebateScore.fromServerData(url,map,RebateScoreBean.class);
    }


    @Override
    public void successOn(RebateScoreBean rebateScoreBean) {
       // RebateScoreBean.ObjectBean object = rebateScoreBean.getObject();
    }

    @Override
    public void errorOn(String message, int code) {

    }
}
