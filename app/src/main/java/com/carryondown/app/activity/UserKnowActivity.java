package com.carryondown.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.carryondown.app.R;
import com.carryondown.app.base.BaseActivity;

/**
 * Created by Administrator on 2017/4/27.
 */

public class UserKnowActivity  extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_know);
        setTitle("用户指南");
        showContentView();
    }
    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, UserKnowActivity.class);
        mContext.startActivity(intent);
    }
}