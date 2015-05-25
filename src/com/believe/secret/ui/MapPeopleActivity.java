package com.believe.secret.ui;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.believe.secret.R;

import android.os.Bundle;
/**
 * 地图显示附近的人
 * 
 * @author zynick
 *
 */
public class MapPeopleActivity extends ActivityBase {
	MapView mMapView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.activity_map_people);
		mMapView = (MapView)findViewById(R.id.bmapView);
		initTopBarForLeft("附近的人");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mMapView.onResume();
	}
	
	protected void onDestroy(){
		super.onDestroy();
		mMapView.onDestroy();
	}
	
	protected void onPause(){
		super.onPause();
		mMapView.onPause();
	}
}
