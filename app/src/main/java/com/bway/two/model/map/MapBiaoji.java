package com.bway.two.model.map;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.bway.two.R;

/**
 * autor: 李金涛.
 * date:2017/8/15
 */


public class MapBiaoji extends LinearLayout {
    public MapBiaoji(Context context) {
        this(context,null);
    }

    public MapBiaoji(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MapBiaoji(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.map_biaozhu, this);
    }
}
