package com.carryondown.app.activity.route;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.services.route.RideStep;
import com.carryondown.app.R;
import com.carryondown.app.util.AMapUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 骑行路线详情页adapter
 * 
 */
public class RideSegmentListAdapter extends BaseAdapter {
	private Context mContext;
	private List<RideStep> mItemList = new ArrayList<RideStep>();

	public RideSegmentListAdapter(Context applicationContext,
			List<RideStep> steps) {
		mContext = applicationContext;
		mItemList.add(new RideStep());
		for (RideStep rideStep : steps) {
			mItemList.add(rideStep);
		}
		mItemList.add(new RideStep());
	}

	@Override
	public int getCount() {
		return mItemList.size();
	}

	@Override
	public Object getItem(int position) {
		return mItemList.get(position);
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
			convertView = View.inflate(mContext, R.layout.item_bus_segment,
					null);
			holder.lineName = (TextView) convertView
					.findViewById(R.id.bus_line_name);
			holder.dirIcon = (ImageView) convertView
					.findViewById(R.id.bus_dir_icon);
			holder.dirUp = (ImageView) convertView
					.findViewById(R.id.bus_dir_icon_up);
			holder.dirDown = (ImageView) convertView
					.findViewById(R.id.bus_dir_icon_down);
			holder.splitLine = (ImageView) convertView
					.findViewById(R.id.bus_seg_split_line);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final RideStep item = mItemList.get(position);
		if (position == 0) {
			holder.dirIcon.setImageResource(R.drawable.dir_start);
			holder.lineName.setText("出发");
			holder.dirUp.setVisibility(View.INVISIBLE);
			holder.dirDown.setVisibility(View.VISIBLE);
			holder.splitLine.setVisibility(View.INVISIBLE);
			return convertView;
		} else if (position == mItemList.size() - 1) {
			holder.dirIcon.setImageResource(R.drawable.dir_end);
			holder.lineName.setText("到达终点");
			holder.dirUp.setVisibility(View.VISIBLE);
			holder.dirDown.setVisibility(View.INVISIBLE);
			return convertView;
		} else {
			holder.splitLine.setVisibility(View.VISIBLE);
			holder.dirUp.setVisibility(View.VISIBLE);
			holder.dirDown.setVisibility(View.VISIBLE);
			String actionName = item.getAction();
			int resID = AMapUtil.getWalkActionID(actionName);
			holder.dirIcon.setImageResource(resID);
			holder.lineName.setText(item.getInstruction());
			return convertView;
		}
		
	}

	private class ViewHolder {
		TextView lineName;
		ImageView dirIcon;
		ImageView dirUp;
		ImageView dirDown;
		ImageView splitLine;
	}

}
