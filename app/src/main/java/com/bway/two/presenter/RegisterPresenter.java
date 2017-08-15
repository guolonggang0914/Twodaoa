package com.bway.two.presenter;

import android.content.Context;

import com.bway.two.model.bean.RegisterBean;
import com.bway.two.model.net.okhttp.HttpManager;
import com.bway.two.model.net.okhttp.OkhttpEnginen;
import com.bway.two.view.IMview.IMLogin;
import com.bway.two.view.IMview.customcallback.EntityCallBack;

import java.util.Map;

/**
 * 注册逻辑处理类
 * autor: 李金涛.
 * date:2017/8/10
 */


public class RegisterPresenter {
    private Context context;
    private OkhttpEnginen  okhttpEnginen;
    private IMLogin imLogin;
    private int mCode;
    public RegisterPresenter(Context context) {
        this.context = context;
        okhttpEnginen = new OkhttpEnginen();
    }
    //连接
    public void onAttach(IMLogin imLogin){
        this.imLogin = imLogin;
    }
    //解除连接
    public void onDetach(){
        if(imLogin!=null){
            imLogin=null;
        }
    }

    public <T>void loadUrlbypost(String url, Map<String,Object> map, final int code){
        this.mCode = code;
        HttpManager.getInstance().post(url, map, new EntityCallBack<RegisterBean>() {



            @Override
            public void onSuccess(RegisterBean registerBean) {
                imLogin.onSucceed(registerBean,mCode);
            }

            @Override
            public void onFailure(String message, int error) {
                imLogin.onError(error,message);
            }
        });
    }

}
