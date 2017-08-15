package com.bway.two.view.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bway.two.R;
import com.bway.two.model.base.BaseActivity;
import com.bway.two.model.bean.FirstEvent;
import com.bway.two.model.bean.LoginBean;
import com.bway.two.presenter.LoginPresenter;
import com.bway.two.view.IMview.IMLoginTwo;
import com.bway.two.view.fragment.MineFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bway.two.R.id.zuce_phone;
import static com.bway.two.R2.id.zhuce_tongyi;

public class ZhangHaoLoginActivity extends BaseActivity implements View.OnClickListener, IMLoginTwo<LoginBean>{


    @BindView(R.id.zhanghao_fh)
    ImageView zhanghaoFh;
    @BindView(R.id.zhanghao_phone)
    EditText zhanghaoPhone;
    @BindView(R.id.zhanghao_pwd)
    EditText zhanghaoPwd;
    @BindView(R.id.zhanghao_login)
    Button zhanghaoLogin;
    @BindView(R.id.zhanghao_wangjipwd)
    TextView zhanghaoWangjipwd;
    private LoginPresenter presenter;
    private String  url = "http://123.57.33.185:8088/user/login";
    private Map<String,Object>  map = new HashMap<>();
  private static final String TAG = "ZhangHaoLoginActivity";
    private android.app.FragmentManager fragmentManager;

    @Override
    public int getLayout() {
        return R.layout.activity_zhang_hao_login;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
         zhanghaoFh.setOnClickListener(this);
        zhanghaoLogin.setOnClickListener(this);
        presenter = new LoginPresenter(this);
        presenter.onAttach(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.zhanghao_fh:
                finish();
                break;
            case R.id.zhanghao_login:
                String phone = zhanghaoPhone.getText().toString().trim();
                String pwd = zhanghaoPwd.getText().toString().trim();
                map.put("phone",phone);
                map.put("password",pwd);
                map.put("merchant",0);
               presenter.loadUrlbyPost(url,map);
                if (zhanghaoPhone.length() != 11) {
                    Toast.makeText(ZhangHaoLoginActivity.this, "手机号位数不够", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phone)&&TextUtils.isEmpty(pwd)){
                    Toast.makeText(ZhangHaoLoginActivity.this,"参数不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                EventBus.getDefault().post(new FirstEvent(phone));
                finish();

        }
    }

    @Override
    public void onSucceed(LoginBean loginBean) {
        String s = loginBean.getDescirption();
        LoginBean.ObjectBean bean = loginBean.getObject();
        Log.i(TAG, "onSucceed: "+s);
        String token = bean.getToken();
        //69f7a6c8de0ef5c9c8d47f7570137db9
        Log.i(TAG, "onSucceed: "+token);

    }

    @Override
    public void onError(int code, String err) {

    }

    @Override
    public void checkInfo(boolean isNull, String msg) {

    }

}
