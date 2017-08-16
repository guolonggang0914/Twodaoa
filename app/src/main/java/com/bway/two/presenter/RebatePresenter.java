package com.bway.two.presenter;
import android.util.Log;

import com.bway.two.model.bean.InformationRebate;
import com.bway.two.model.bean.RecyclerViewItem;
import com.bway.two.model.net.okhttp.HttpManager;
import com.bway.two.model.net.okhttp.OkhttpEnginen;
import com.bway.two.view.IMview.customcallback.CallBack;
import com.bway.two.view.IMview.customcallback.EntityCallBack;
import com.bway.two.view.IMview.customcallback.RebateView;
import com.bway.two.view.IMview.customcallback.RebateViewItem;
import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class RebatePresenter{
    private HttpManager okhttp;
    private RebateView rebateview;
    private RebateViewItem rebateviewitem;
    public static final String TAG ="RebatePresenter";
    public RebatePresenter(RebateView rebateView,RebateViewItem rebateviewitem)
    {
        okhttp=HttpManager.getInstance();
        this.rebateview=rebateView;
        this.rebateviewitem=rebateviewitem;
    }
    public <T>void getServerUrl(String url, Map<String,Object> map, Class<T> clazz) {
        okhttp.post(url, map, new EntityCallBack<InformationRebate>() {
            @Override
            public void onSuccess(InformationRebate o) {

                 rebateview.onSuceess(o);

            }

            @Override
            public void onFailure(String message, int error) {

            }
        });
    }

    public <T>void getFromServerItem(String url,Map<String,Object> map,Class<T> clazz) {
        okhttp.post(url, map, new EntityCallBack<RecyclerViewItem>() {
            @Override
            public void onSuccess(RecyclerViewItem o) {
                rebateviewitem.successOn(o);
                Log.e(TAG, "onSuccess: "+ o.toString());
            }

            @Override
            public void onFailure(String message, int error) {

            }
        });
    }
}
