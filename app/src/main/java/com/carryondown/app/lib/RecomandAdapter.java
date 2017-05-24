/**  
 * Project Name:Android_Car_Example  
 * File Name:RecomandAdapter.java  
 * Package Name:com.amap.api.car.example  
 * Date:2015年4月3日上午11:29:45  
 *  
 */

package com.carryondown.app.lib;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.carryondown.app.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName:RecomandAdapter <br/>
 * Function: 显示的poi列表 <br/>
 * Date: 2015年4月3日 上午11:29:45 <br/>
 * 
 * @author yiyi.qi
 * @version
 * @since JDK 1.6
 * @see
 */
public class RecomandAdapter extends BaseAdapter {

//	PositionEntity[] entities = new PositionEntity[] {
//			new PositionEntity(39.908722, 116.397496, "天安门","010"),
//			new PositionEntity(39.91141, 116.411306, "王府井","010"),
//			new PositionEntity(39.908342, 116.375121, "西单","010"),
//			new PositionEntity(39.990949, 116.481090, "方恒国际中心","010"),
//			new PositionEntity(39.914529, 116.316648, "玉渊潭公园","010"),
//			new PositionEntity(39.999093, 116.273945, "颐和园","010"),
//			new PositionEntity(39.999022, 116.324698, "清华大学","010"),
//			new PositionEntity(39.982940, 116.319802, "中关村","010"),
//			new PositionEntity(39.933708, 116.454185, "三里屯","010"),
//			new PositionEntity(39.941627, 116.435584, "东直门","010") };

	private List<PositionEntity> mPositionEntities;

	private Context mContext;

	public RecomandAdapter(Context context) {
		mContext = context;
//		mPositionEntities = Arrays.asList(entities);
		mPositionEntities = new ArrayList<>();
	
	}

	public void setPositionEntities(List<PositionEntity> entities) {
		this.mPositionEntities = entities;

	}

	@Override
	public int getCount() {

		// TODO Auto-generated method stub
		return mPositionEntities.size();
	}

	@Override
	public Object getItem(int position) {

		return mPositionEntities.get(position);
	}

	@Override
	public long getItemId(int position) {

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.view_recommond,
					null);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder =  (ViewHolder)convertView.getTag();
		}
		viewHolder.tv_address.setText(mPositionEntities.get(position).address);
		viewHolder.tv_city.setText(mPositionEntities.get(position).city);
		return convertView;
	}

	class ViewHolder {

		public TextView tv_address,tv_city;

		public ViewHolder(View itemView) {
			tv_address = (TextView) itemView.findViewById(R.id.tv_address);
			tv_city = (TextView) itemView.findViewById(R.id.tv_city);
		}

	}

	 

}
