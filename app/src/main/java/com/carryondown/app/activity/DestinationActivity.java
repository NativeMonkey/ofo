package com.carryondown.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.carryondown.app.MainActivity;
import com.carryondown.app.R;
import com.carryondown.app.lib.InputTipTask;
import com.carryondown.app.lib.PoiSearchTask;
import com.carryondown.app.lib.PositionEntity;
import com.carryondown.app.lib.RecomandAdapter;
import com.carryondown.app.lib.RouteTask;


/**
 * Created by yiyi.qi on 2016/10/18.
 */

public class DestinationActivity extends Activity implements View.OnClickListener,TextWatcher,AdapterView.OnItemClickListener {

    private ListView mRecommendList;

    private ImageView mBack_Image;


    private EditText mDestinaionText;

    private RecomandAdapter mRecomandAdapter;

    private RouteTask mRouteTask;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        mRecommendList=(ListView) findViewById(R.id.recommend_list);
        mBack_Image=(ImageView) findViewById(R.id.destination_back);
        mBack_Image.setOnClickListener(this);


        mDestinaionText=(EditText) findViewById(R.id.destination_edittext);
        mDestinaionText.addTextChangedListener(this);
        mRecomandAdapter=new RecomandAdapter(getApplicationContext());
        mRecommendList.setAdapter(mRecomandAdapter);
        mRecommendList.setOnItemClickListener(this);

        mRouteTask=RouteTask.getInstance(getApplicationContext());
    }

    @Override
    public void afterTextChanged(Editable arg0) {

        // TODO Auto-generated method stub

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {

        // TODO Auto-generated method stub

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(RouteTask.getInstance(getApplicationContext()).getStartPoint()==null){
            Toast.makeText(getApplicationContext(), "检查网络，Key等问题", Toast.LENGTH_SHORT).show();
            return;
        }
        InputTipTask.getInstance( mRecomandAdapter).searchTips(getApplicationContext(),s.toString(),
                RouteTask.getInstance(getApplicationContext()).getStartPoint().city);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.destination_back:
                Intent intent =new Intent(DestinationActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                            long id) {

        PositionEntity entity = (PositionEntity) mRecomandAdapter.getItem(position);
        if (entity.latitue == 0 && entity.longitude == 0) {
            PoiSearchTask poiSearchTask=new PoiSearchTask(getApplicationContext(), mRecomandAdapter);
            poiSearchTask.search(entity.address,RouteTask.getInstance(getApplicationContext()).getStartPoint().city);

        } else {
            mRouteTask.setEndPoint(entity);
            mRouteTask.search();
            Intent intent = new Intent(DestinationActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            finish();
        }
    }

}

