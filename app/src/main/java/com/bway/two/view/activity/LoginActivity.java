package com.bway.two.view.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bway.two.R;
import com.bway.two.model.base.BaseActivity;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private TextView login_other;
    private TextView login_zhuce;
    private Dialog alertDialog;
    private ImageView quxiao;
    private Button login_weibo;
    private Button login_phone;
    private static final String TAG = "MainActivity";
    private static final String APP_ID = "1105602574";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        //传入参数APPID和全局Context上下文
        mTencent = Tencent.createInstance(APP_ID,LoginActivity.this.getApplicationContext());
        getDialog();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        login_other = (TextView) findViewById(R.id.login_other);
        login_zhuce = (TextView) findViewById(R.id.login_zhuce);
        quxiao = (ImageView) findViewById(R.id.quxiao);
        login_weibo = (Button) findViewById(R.id.login_weibo);
        login_phone = (Button) findViewById(R.id.login_phone);

    }

    @Override
    public void initData() {
        login_other.setOnClickListener(this);
        login_zhuce.setOnClickListener(this);
        quxiao.setOnClickListener(this);
        login_weibo.setOnClickListener(this);
        login_phone.setOnClickListener(this);
    }

    private void getDialog() {
        alertDialog = new Dialog(LoginActivity.this);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        final View view = LayoutInflater.from(LoginActivity.this).inflate(R.layout.dialog_min,null);
        RadioButton min_qq = view.findViewById(R.id.min_qq);
        RadioButton  min_weixin = view.findViewById(R.id.min_weixin);
        RadioButton min_zhanghao = view.findViewById(R.id.min_zhanghao);
        min_zhanghao.setOnClickListener(this);
        min_qq.setOnClickListener(this);
        alertDialog.setCanceledOnTouchOutside(false);
        Window dialogWindow = alertDialog.getWindow();
        dialogWindow.setGravity(Gravity.HORIZONTAL_GRAVITY_MASK | Gravity.BOTTOM);
        view.findViewById(R.id.min_quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           alertDialog.dismiss();
            }
        });
        alertDialog.setContentView(view);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.login_other:
                alertDialog.show();
                break;
            case  R.id.login_zhuce:
                Intent  intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case  R.id.quxiao:
                finish();
                break;
            case  R.id.login_phone:
                startActivity(new Intent(LoginActivity.this,NumberActivity.class));
                break;
            case  R.id.login_weibo:

                break;
            case  R.id.min_zhanghao:
            startActivity(new Intent(LoginActivity.this,ZhangHaoLoginActivity.class));
                break;
            case R.id.min_qq:
                /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
                 官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
                 第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
                mIUiListener = new BaseUiListener();
                //all表示获取所有权限
                mTencent.login(LoginActivity.this,"all", mIUiListener);
                break;
        }
    }
    public void buttonLogin(View v){
        /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
         官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
         第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
        mIUiListener = new BaseUiListener();
        //all表示获取所有权限
        mTencent.login(LoginActivity.this,"all", mIUiListener);
    }

    /**
     * 自定义监听器实现IUiListener接口后，需要实现的3个方法
     * onComplete完成 onError错误 onCancel取消
     */
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            Toast.makeText(LoginActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken,expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(),qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG,"登录成功"+response.toString());
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG,"登录失败"+uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG,"登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



        @Override
        public void onError(UiError uiError) {
            Toast.makeText(LoginActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this, "授权取消", Toast.LENGTH_SHORT).show();

        }

    }

    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_LOGIN){
            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

