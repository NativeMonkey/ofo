package com.carryondown.app.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.carryondown.app.R;
import com.carryondown.app.base.BaseActivity;
import com.carryondown.app.databinding.ActivityMyTripBinding;
import com.carryondown.app.util.AMapUtil;
import com.carryondown.app.util.ScreenUtil;

/**
 * Created by Administrator on 2017/4/27.
 */

public class MyTripActivity extends BaseActivity<ActivityMyTripBinding> {
    private AMap aMap;
    //绘制一条实线
    private double Lat_A = 30.274742;
    private double Lon_A = 119.987131;

    private double Lat_B = 30.274742;
    private double Lon_B = 119.997531;

    private double Lat_C = 30.285042;
    private double Lon_C = 119.997531;

    private double Lat_D = 30.285042;
    private double Lon_D = 119.999999;
    private BitmapDescriptor lineBitmap = null;
    private LatLonPoint mStartPoint = null;//起点，116.335891,39.942295
    private LatLonPoint mEndPoint = null;//终点，116.481288,39.995576

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trip);
        setTitle("我的行程");
        showContentView();
        bindingView.map.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = bindingView.map.getMap();
            aMap.getUiSettings().setZoomControlsEnabled(false);
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(30.274742, 119.987131), 15));
        }
        mStartPoint = new LatLonPoint(Lat_A, Lon_A);
        ;
        mEndPoint = new LatLonPoint(Lat_D, Lon_D);
        setfromandtoMarker();
        addPolylinessoild();
    }

    //绘制一条实线
    private void addPolylinessoild() {
        LatLng A = new LatLng(Lat_A, Lon_A);
        LatLng B = new LatLng(Lat_B, Lon_B);
        LatLng C = new LatLng(Lat_C, Lon_C);
        LatLng D = new LatLng(Lat_D, Lon_D);
        if (lineBitmap == null) {
            lineBitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.ic_map_go);
        }
        aMap.addPolyline((new PolylineOptions())
                .add(A, B, C, D)
                .width(18f)
                .setCustomTexture(lineBitmap));
        zoomToSpan();
    }

    private void setfromandtoMarker() {
        aMap.addMarker(new MarkerOptions()
                .position(AMapUtil.convertToLatLng(mStartPoint))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.start)));
        aMap.addMarker(new MarkerOptions()
                .position(AMapUtil.convertToLatLng(mEndPoint))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.end)));
    }

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, MyTripActivity.class);
        mContext.startActivity(intent);
    }

    /**
     * 移动镜头到当前的视角。
     *
     * @since V2.1.0
     */
    public void zoomToSpan() {
        try {
            LatLngBounds bounds = getLatLngBounds();
            aMap.animateCamera(CameraUpdateFactory
                    .newLatLngBounds(bounds, ScreenUtil.sysWidth(), ScreenUtil.sysHeight(), 50));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    protected LatLngBounds getLatLngBounds() {
        LatLngBounds.Builder b = LatLngBounds.builder();
        b.include(new LatLng(Lat_A, Lon_A));
        b.include(new LatLng(Lat_D, Lon_D));
        return b.build();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        bindingView.map.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        bindingView.map.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        bindingView.map.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        bindingView.map.onDestroy();
    }

}
