package com.bway.two.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bway.two.R;
import com.bway.two.model.bean.RebateScoreBean;
import com.bway.two.presenter.RebateAlreadyPresenter;
import com.bway.two.view.IMview.customcallback.RebateAlready;

import java.util.HashMap;
import java.util.Map;

/**   author  郭龙刚
 *   desc  已返利的界面  里面没有数据
 * Created by Administrator on 2017/8/14 0014.
 */

public class RebateslFragment extends Fragment implements RebateAlready<RebateScoreBean>{
    private static final String TAG ="RebateslFragment" ;
    private String url="http://123.57.33.185:8088/cashback/list";
    private RebateAlreadyPresenter presenter;
    //2dbae1f3fda438301a33e1d0cfd97a34

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rebates,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getFromServerData(url);
    }

    private void getFromServerData(String url) {
        presenter = new RebateAlreadyPresenter(this);
        Map<String,Object> map=new HashMap<>();
        map.put("status","1");
        map.put("token","2dbae1f3fda438301a33e1d0cfd97a34");
        presenter.setDataFromServer(url,map);
    }

    @Override
    public void succcessOn(RebateScoreBean rebateScoreBean) {
        Log.e(TAG, "succcessOn: "+rebateScoreBean);
    }

    @Override
    public void error(String message, int code) {

    }
}
