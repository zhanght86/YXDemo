package com.xiaokun.xiusou.demo6.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.xiaokun.xiusou.demo6.Base.BaseActivity;
import com.xiaokun.xiusou.demo6.CustomView.LuckyPan;
import com.xiaokun.xiusou.demo6.R;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public class LuckPanActivity extends BaseActivity
{
    LuckyPan mLuckyPan;
    ImageView mStartBtn;
    private int index = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLuckyPan = (LuckyPan) findViewById(R.id.id_luckypan);
        mStartBtn = (ImageView) findViewById(R.id.id_start_btn);

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mLuckyPan.isStart()) {//如果当前状态是 没有开始 状态
                    mLuckyPan.luckyStart(index);
                    mStartBtn.setImageResource(R.mipmap.stop);
                } else {//如果当前状态是 已经开始 状态
                    if (!mLuckyPan.isShouldEnd()) {//停止按钮已经按过了
                        mLuckyPan.luckyEnd();
                        mStartBtn.setImageResource(R.mipmap.start);
                    }
                }
            }
        });


        mLuckyPan.setOnFinishListener(new LuckyPan.OnFinishLuckyPan() {
            @Override
            public void onFinish() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LuckPanActivity.this, "您抽中：" + LuckyPan.mStrs[index],
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    public int getLayout()
    {
        return R.layout.activity_luckpan;
    }
}
