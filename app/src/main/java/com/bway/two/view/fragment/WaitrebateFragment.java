package com.bway.two.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bway.two.R;
import com.bway.two.model.bean.RebateScoreBean;
import com.bway.two.presenter.WaitRebateRresenter;
import com.bway.two.view.IMview.customcallback.RebateWait;

import java.util.HashMap;
import java.util.Map;

/**anthor guolonggang
 * desc 带返利界面  接口里面没有数据
 * Created by Administrator on 2017/8/14 0014.
 */

public class WaitrebateFragment extends Fragment implements RebateWait<RebateScoreBean> {
    private String url="http://123.57.33.185:8088/cashback/list";
    private WaitRebateRresenter presenter;
    //

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_waitrebate,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDataFromServer(url);
    }

    private void getDataFromServer(String url) {
        presenter = new WaitRebateRresenter(this);
        Map<String,Object> map=new HashMap<>();
        map.put("token","t2dbae1f3fda438301a33e1d0cfd97a34");
        map.put("status","0");
        presenter.setDataFromServer(url,map);
    }


    @Override
    public void successOn(RebateScoreBean rebateScoreBean) {

    }

    @Override
    public void errorOn(String message, int code) {

    }
}
