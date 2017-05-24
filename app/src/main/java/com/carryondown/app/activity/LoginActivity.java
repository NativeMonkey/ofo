package com.carryondown.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.carryondown.app.R;
import com.carryondown.app.base.BaseActivity;
import com.carryondown.app.databinding.ActivityLoginBinding;

/**
 * Created by Administrator on 2017/4/27.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("手机登陆");
        showContentView();
    }
    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }
}
