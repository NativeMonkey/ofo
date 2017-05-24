package com.carryondown.app.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.carryondown.app.R;

/**
 * Created by Administrator on 2017/4/24.
 */

public class LoadDialog extends DialogFragment {

    public static LoadDialog getInstance()
    {
        return FirstQuote.instance;
    }

    //在第一次被引用时被加载
    static class FirstQuote
    {
        private static LoadDialog instance = new LoadDialog();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        View view = inflater.inflate(R.layout.load, container);
        Animation operatingAnim = AnimationUtils.loadAnimation(view.getContext(), R.anim.tips);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        ImageView iv_load = (ImageView)view.findViewById(R.id.iv_load);
        iv_load.setAnimation(operatingAnim);
        return view;
    }
}
