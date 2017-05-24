package com.carryondown.app.activity.route;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.DriveStep;
import com.amap.api.services.route.TMC;
import com.carryondown.app.R;
import com.carryondown.app.base.BaseActivity;
import com.carryondown.app.util.AMapUtil;

import java.util.List;

/**
 * 驾车路线详情
 */
public class DriveRouteDetailActivity extends BaseActivity {
	private DrivePath mDrivePath;
	private DriveRouteResult mDriveRouteResult;
	private TextView mTitleDriveRoute, mDesDriveRoute;
	private ListView mDriveSegmentList;
	private DriveSegmentListAdapter mDriveSegmentListAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_route_detail);
		setTitle("驾车路线详情");
		showContentView();
		getIntentData();
		init();
	}

	private void init() {
		mTitleDriveRoute = (TextView) findViewById(R.id.firstline);
		mDesDriveRoute = (TextView) findViewById(R.id.secondline);
		String dur = AMapUtil.getFriendlyTime((int) mDrivePath.getDuration());
		String dis = AMapUtil.getFriendlyLength((int) mDrivePath
				.getDistance());
		mTitleDriveRoute.setText(dur + "(" + dis + ")");
		int taxiCost = (int) mDriveRouteResult.getTaxiCost();
		mDesDriveRoute.setText("打车约"+taxiCost+"元");
		mDesDriveRoute.setVisibility(View.VISIBLE);
		configureListView();
	}

	private void configureListView() {
		mDriveSegmentList = (ListView) findViewById(R.id.bus_segment_list);
		mDriveSegmentListAdapter = new DriveSegmentListAdapter(
				this.getApplicationContext(), mDrivePath.getSteps());
		mDriveSegmentList.setAdapter(mDriveSegmentListAdapter);
	}

	private void getIntentData() {
		Intent intent = getIntent();
		if (intent == null) {
			return;
		}
		mDrivePath = intent.getParcelableExtra("drive_path");
		mDriveRouteResult = intent.getParcelableExtra("drive_result");
		for (int i = 0; i < mDrivePath.getSteps().size(); i++) {
			DriveStep step = mDrivePath.getSteps().get(i);
			List<TMC> tmclist = step.getTMCs();
			for (int j = 0; j < tmclist.size(); j++) {
				String s = ""+tmclist.get(j).getPolyline().size();
				Log.i("MY", s+tmclist.get(j).getStatus()
						+tmclist.get(j).getDistance()
						+tmclist.get(j).getPolyline().toString());
			}
		}
	}
}
