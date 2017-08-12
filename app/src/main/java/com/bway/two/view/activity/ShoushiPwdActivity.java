package com.bway.two.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.bway.two.R;

public class ShoushiPwdActivity extends AppCompatActivity {
    private GestureLockViewGroup mGestureLockViewGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoushi_pwd);
        mGestureLockViewGroup = (GestureLockViewGroup) findViewById(R.id.id_gestureLockViewGroup);
        mGestureLockViewGroup.setAnswer(new int[]{1,2,3,6,9});
        mGestureLockViewGroup
                .setOnGestureLockViewListener(new GestureLockViewGroup.OnGestureLockViewListener()
                {

                    @Override
                    public void onUnmatchedExceedBoundary()
                    {
                        Toast.makeText(ShoushiPwdActivity.this, "错误5次...",
                                Toast.LENGTH_SHORT).show();
                        mGestureLockViewGroup.setUnMatchExceedBoundary(5);
                    }

                    @Override
                    public void onGestureEvent(boolean matched)
                    {
                        Toast.makeText(ShoushiPwdActivity.this, matched+"",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onBlockSelected(int cId)
                    {
                    }
                });
    }

}
