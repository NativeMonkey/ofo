package com.carryondown.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.carryondown.app.R;
import com.carryondown.app.base.BaseActivity;

/**
 * Created by Administrator on 2017/4/27.
 */

public class MyMessageActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);
        setTitle("我的消息");
        showContentView();
    }
    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, MyMessageActivity.class);
        mContext.startActivity(intent);
    }
}
