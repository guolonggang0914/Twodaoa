package com.bway.two.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bway.two.R;
import com.bway.two.model.bean.InformationRebate;
import com.bway.two.model.bean.RecyclerViewItem;
import com.bway.two.presenter.RebatePresenter;
import com.bway.two.view.IMview.customcallback.RebateView;
import com.bway.two.view.IMview.customcallback.RebateViewItem;
import com.bway.two.view.activity.PlanActivity;
import com.bway.two.view.activity.QueryActivity;
import com.bway.two.view.adapter.ReBateAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 作者   郭龙刚
 *
 * @data 2017/08/10
 * 类的用途:返利界面
 */

public class RebateFragment extends Fragment implements RebateView<InformationRebate>, RebateViewItem<RecyclerViewItem> {
    private static final String TAG = "RebateFragment";
    @BindView(R.id.fragment_rebate_recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.fragment_rebate_textview)
    TextView fragmentRebateTextview;
    @BindView(R.id.fragment_rebate_more)
    TextView fragmentRebateMore;
    @BindView(R.id.fragment_rebate_ll)
    RelativeLayout fragmentRebateLl;
    @BindView(R.id.fragment_rebate_show_more)
    TextView fragmentRebateShowMore;
    @BindView(R.id.calender)
    ImageView calender;
    @BindView(R.id.txt_first)
    TextView txtFirst;
    @BindView(R.id.txt_230)
    TextView txt230;
    @BindView(R.id.fragment_rebate_price)
    TextView text_price;
    @BindView(R.id.fragment_rebate_num)
    TextView text_num;
    private int position = 2;
    private LinearLayoutManager linearLayoutManager;
    private ReBateAdapter adapter;
    private String url = "http://123.57.33.185:8088/cashback/countCashback";
    private String httpUrl = "http://123.57.33.185:8088/user/cashback/plan";
    private RebatePresenter rebatepresenter;
    private List<RecyclerViewItem.ObjectBean> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rebate, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        getServerUrl();
        getFromServerItem();
    }

    private void getFromServerItem() {
        Map<String, Object> hashmap = new HashMap<>();
        hashmap.put("token", "faf9105720d000f7bcea972fabb4b518");
        rebatepresenter.getFromServerItem(httpUrl, hashmap, RecyclerViewItem.class);
    }

    private void getServerUrl() {
        rebatepresenter = new RebatePresenter(this, this);
        Map<String, Object> map = new HashMap<>();
        map.put("status", "1");
        map.put("token", "2dbae1f3fda438301a33e1d0cfd97a34");
        rebatepresenter.getServerUrl(url, map, InformationRebate.class);
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(linearLayoutManager);
        adapter = new ReBateAdapter(getActivity());
        recyclerview.setAdapter(adapter);
    }

    @OnClick({R.id.fragment_rebate_show_more, R.id.fragment_rebate_query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_rebate_show_more:
                if (adapter.getItemCount() == position) {
                    adapter.getCount(position);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                } else {
                    adapter.setData(list);
                    adapter.notifyDataSetChanged();
                }


                break;
            case R.id.fragment_rebate_query:
                Intent it = new Intent(getActivity(), QueryActivity.class);
                startActivity(it);
                break;

        }


    }

    @Override
    public void onSuceess(InformationRebate informationRebate) {
        double waitCashback = informationRebate.getObject().getWaitCashback();
        int countReally = informationRebate.getObject().getCountReally();
        text_price.setText(waitCashback + "");
        text_num.setText(countReally + "");
    }

    @Override
    public void onError(String msg, int code) {

    }

    @Override
    public void successOn(RecyclerViewItem recyclerViewItem) {
        list.addAll(recyclerViewItem.getObject());
        adapter.setData(list);
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new ReBateAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Toast.makeText(getActivity(), "被点击了" + list.get(postion), Toast.LENGTH_LONG).show();
                Intent it = new Intent(getActivity(), PlanActivity.class);
                it.putExtra("key", list.get(postion).getRecordCoding());
                it.putExtra("keys", list.get(postion).getIntegralStyle());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                it.putExtra("keypass", simpleDateFormat.format(list.get(postion).getCashbackSpecificDate()));
                startActivity(it);
            }
        });
    }

    @Override
    public void errorOn(String message, int code) {

    }


}
