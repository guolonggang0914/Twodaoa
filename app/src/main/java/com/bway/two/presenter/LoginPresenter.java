package com.bway.two.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.bway.two.utils.NetUtils.NetWorkUtils;
import com.bway.two.view.IMview.IMLogin;

import java.util.Map;

/**
 * 登录逻辑处理类
 * autor: 李金涛.
 * date:2017/8/10
 */


public class LoginPresenter {

    private IMLogin imLogin;
    private Context context;
    public LoginPresenter(Context context){
        this.context = context;
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
    public <T>void loadUrlbyget(String url, Map<String,Object> map){
        /*HttpManager.getInstance().get(url, map, new EntityCallBack<>() {

            @Override
            public void onSuccess(Object o) {
                imLogin.onSucceed(o);
            }

            @Override
            public void onFailure(String message, int error) {
                imLogin.onError(error,message);
            }
        });*/
    }


    /**
     * 判断用户输入的信息是否为空
     * @param uerName
     * @param userPassword
     */
    public void checkInfo(String uerName,String userPassword){

        if(!NetWorkUtils.isNetworkConnected(context)){
            imLogin.checkInfo(false,"无网络连接");
            return;
        }
        if(TextUtils.isEmpty(uerName)){
            imLogin.checkInfo(false,"请输入您的用户名");
            return;
        }
        if(TextUtils.isEmpty(userPassword)){
            imLogin.checkInfo(false,"请输入您的密码");
            return;
        }
        imLogin.checkInfo(true,"");
    }
}
