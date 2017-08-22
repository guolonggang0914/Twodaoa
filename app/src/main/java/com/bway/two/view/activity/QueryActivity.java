package com.bway.two.view.activity;
/*
  author  guolonggang
  类的用途 :记录查询页面
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bway.two.R;
import com.bway.two.model.base.BaseActivity;
import com.bway.two.view.fragment.IntergralFragment;
import com.bway.two.view.fragment.RebateslFragment;
import com.bway.two.view.fragment.WaitrebateFragment;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class QueryActivity extends BaseActivity {
    @BindView(R.id.query_jifen)
    RadioButton queryJifen;
    @BindView(R.id.query_framlayout)
    FrameLayout frameLayout;

    @BindView(R.id.query_fanli)
    RadioButton queryFanli;
    @BindView(R.id.query_waitfanli)
    RadioButton queryWaitfanli;
    @BindView(R.id.query_rg)
    RadioGroup queryRg;
    @BindView(R.id.query_fanhui)
    ImageView imageView;
    private IntergralFragment integeral;
    private RebateslFragment rebates;
    private WaitrebateFragment wait;
    private FragmentTransaction transaction;
    private FragmentManager manager;
    @Override
    public int getLayout() {
        return R.layout.activity_query;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setDefaultFragment();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QueryActivity.this.finish();
            }
        });
    }



    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
    private void setDefaultFragment() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        integeral = new IntergralFragment();
        rebates = new RebateslFragment();
        wait = new WaitrebateFragment();
        transaction.add(R.id.query_framlayout,integeral).show(integeral);
        transaction.add(R.id.query_framlayout,rebates).hide(rebates);
        transaction.add(R.id.query_framlayout,wait).hide(wait);
        transaction.commit();

    }

    @OnClick({R.id.query_jifen,
            R.id.query_fanli,
            R.id.query_waitfanli,
            R.id.query_rg})
    public void onViewClicked(View view) {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.query_jifen:
                queryJifen.setChecked(true);
                fragmentTransaction.show(integeral).hide(rebates).hide(wait);
                fragmentTransaction.commit();
                break;
            case R.id.query_fanli:
                FragmentTransaction fragmentTransaction1 = manager.beginTransaction();
                queryFanli.setChecked(true);
                fragmentTransaction.show(rebates).hide(integeral).hide(wait);
                fragmentTransaction.commit();
                break;
            case R.id.query_waitfanli:
                queryWaitfanli.setChecked(true);
                FragmentTransaction fragmentTransaction2 = manager.beginTransaction();
                fragmentTransaction.show(wait).hide(integeral).hide(rebates);
                fragmentTransaction.commit();
                break;

        }
    }
}
