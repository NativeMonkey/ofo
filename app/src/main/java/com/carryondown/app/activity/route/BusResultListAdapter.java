package com.carryondown.app.activity.route;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusRouteResult;
import com.carryondown.app.R;
import com.carryondown.app.util.AMapUtil;

import java.util.List;

public class BusResultListAdapter extends BaseAdapter {
	private Context mContext;
	private List<BusPath> mBusPathList;
	private BusRouteResult mBusRouteResult;

	public BusResultListAdapter(Context context, BusRouteResult busrouteresult) {
		mContext = context;
		mBusRouteResult = busrouteresult;
		mBusPathList = busrouteresult.getPaths();
	}
	
	@Override
	public int getCount() {
		return mBusPathList.size();
	}

	@Override
	public Object getItem(int position) {
		return mBusPathList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(mContext, R.layout.item_bus_result, null);
			holder.title = (TextView) convertView.findViewById(R.id.bus_path_title);
			holder.des = (TextView) convertView.findViewById(R.id.bus_path_des);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		final BusPath item = mBusPathList.get(position);
		holder.title.setText(AMapUtil.getBusPathTitle(item));
		holder.des.setText(AMapUtil.getBusPathDes(item));
		
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext.getApplicationContext(),
						BusRouteDetailActivity.class);
				intent.putExtra("bus_path", item);
				intent.putExtra("bus_result", mBusRouteResult);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				mContext.startActivity(intent);
				
			}
		});
		
		return convertView;
	}
	
	private class ViewHolder {
		TextView title;
		TextView des;
	}

}
