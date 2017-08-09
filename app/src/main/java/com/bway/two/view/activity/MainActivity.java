package com.bway.two.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bway.two.R;
import com.bway.two.utils.HttpManager;
import com.bway.two.view.IMview.customcallback.EntityCallBack;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String url = "http://qhb.2dyt.com/MyInterface/userAction_selectAllUser.action?user.sign=a85a7ba1aacd356c728dd0b2fceb8f54&user.currenttimer=1502185312233";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Map<String,Object> map = new HashMap<>();
        HttpManager.getInstance().get(url, map, new EntityCallBack<ArrFriendData>() {

            @Override
            public void onSuccess(ArrFriendData arrFriendData) {

            }

            @Override
            public void onFailure(String message, int error) {

            }
        });
    }



  /*  @Override
    public void onSuccess(ArrFriendData arrFriendData) {

    }

    @Override
    public void onFailure(String message, int error) {

    }*/
}
