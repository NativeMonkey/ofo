/**  
 * Project Name:Android_Car_Example  
 * File Name:PoiSearchTask.java  
 * Package Name:com.amap.api.car.example  
 * Date:2015年4月7日上午11:25:07  
 *  
 */

package com.carryondown.app.lib;

import android.content.Context;
import android.util.Log;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.poisearch.PoiSearch.OnPoiSearchListener;
import com.amap.api.services.poisearch.PoiSearch.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:PoiSearchTask <br/>
 * Function: 简单封装了poi搜索的功能，搜索结果配合RecommendAdapter进行使用显示 <br/>
 * Date: 2015年4月7日 上午11:25:07 <br/>
 * 
 * @author yiyi.qi
 * @version
 * @since JDK 1.6
 * @see
 */
public class PoiSearchTask implements OnPoiSearchListener {

	private Context mContext;

	private RecomandAdapter mRecommandAdapter;

	public PoiSearchTask(Context context, RecomandAdapter recomandAdapter) {
		mContext = context;

		mRecommandAdapter = recomandAdapter;

	}

	public void search(String keyWord, String city) {
		Log.i("MY","search");
		Query query = new PoiSearch.Query(keyWord, "", city);
		query.setPageSize(10); 
		query.setPageNum(0); 

		PoiSearch poiSearch = new PoiSearch(mContext, query);
		poiSearch.setOnPoiSearchListener(this);
		poiSearch.searchPOIAsyn();
	}



	@Override
	public void onPoiSearched(PoiResult poiResult, int resultCode) {
		if (resultCode == AMapException.CODE_AMAP_SUCCESS && poiResult != null) {
			ArrayList<PoiItem> pois=poiResult.getPois();
			if(pois==null){
				return;
			}
			List<PositionEntity> entities=new ArrayList<PositionEntity>();
			for(PoiItem poiItem:pois){
				PositionEntity entity=new PositionEntity(poiItem.getLatLonPoint().getLatitude(),
						poiItem.getLatLonPoint().getLongitude(),poiItem.getTitle()
						,poiItem.getCityName());
				entities.add(entity);
			}
			mRecommandAdapter.setPositionEntities(entities);
			mRecommandAdapter.notifyDataSetChanged();
		}
		//TODO 可以根据app自身需求对查询错误情况进行相应的提示或者逻辑处理
	}

	@Override
	public void onPoiItemSearched(PoiItem poiItem, int i) {

	}
}
