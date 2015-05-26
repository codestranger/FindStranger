package com.believe.secret.ui;
/**
 * ҡһҡҳ�棬ͨ��ҡ���ֻ������ͼ����ʾ����������Ϣ��
 */
import java.util.ArrayList;
import java.util.List;

import cn.bmob.im.task.BRequest;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobGeoPoint;
import cn.bmob.v3.listener.FindListener;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.believe.secret.R;
import com.believe.secret.bean.User;
import com.believe.secret.util.CollectionUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.believe.secret.bean.User;
public class ShakeFindFriendActivity extends ActivityBase{
	
	private SensorManager sensorManager;
	private Vibrator vibrator;
	private double QUERY_KILOMETERS = 1;//Ĭ�ϲ�ѯ1���ﷶΧ�ڵ���
	boolean isSuccess = false; //��ѯ�ɹ�
	private TextView tv;
	public static List<User> nears = new ArrayList<User>();
	public static double latitude;
	public static double longitude;
	LocationClient mLocationClient = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shake_findfriend);
		initTopBarForLeft("ҡһҡ");
		//ҡһҡ����
		sensorManager = (SensorManager) getSystemService (Context.SENSOR_SERVICE);
		Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(listener, sensor, SensorManager. SENSOR_DELAY_NORMAL);
		tv = (TextView)findViewById(R.id.tv_shake);
		//������λ
		mLocationClient = new LocationClient(this);  
		BDLocationListener myListener = new BDLocationListener() {
			@Override
			public void onReceiveLocation(BDLocation location) {
				latitude = location.getLatitude();
				longitude = location.getLongitude();
				Log.v("LOC", longitude+"==="+latitude);
			
			}
		};
		mLocationClient.registerLocationListener(myListener);//ע�������
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(false);
		option.setLocationMode(LocationMode.Hight_Accuracy);//���ö�λģʽ
		option.setCoorType("bd09ll");//���صĶ�λ����ǰٶȾ�γ��,Ĭ��ֵgcj02
		mLocationClient.setLocOption(option);
		mLocationClient.start();
		
		tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getNearPeople(false);
				//callMapActivity();
				
			}
		});
		
	}
	/**
	 * ֹͣ��ȡλ��
	 */
	void stopLoc(){
		mLocationClient.stop();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (sensorManager != null) {
			sensorManager.unregisterListener(listener);
		}
		stopLoc();
	}
	protected void onPause(){
		super.onPause();
		// ֹͣ���
	}
	private SensorEventListener listener = new SensorEventListener() {

		@Override
		public void onSensorChanged(SensorEvent event) {
			// ���ٶȿ��ܻ��Ǹ�ֵ������Ҫȡ���ǵľ���ֵ
			float xValue = Math.abs(event.values[0]);
			float yValue = Math.abs(event.values[1]);
			float zValue = Math.abs(event.values[2]);
			if (xValue > 11 || yValue > 11 || zValue > 11) {
				// ��Ϊ�û�ҡ�����ֻ�������ҡһҡ�߼�
			//	Toast.makeText(ShakeFindFriendActivity.this, "ҡһҡ", Toast.LENGTH_SHORT).show();
		        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		       // long[] pattern = {0, 500, 500, 1000}; // OFF/ON /OFF/ON......                
		       // vibrator.vibrate(pattern, -1); 
		       vibrator.vibrate(new long[]{500,300,500,300}, -1);
		       ShowToast("ҡһҡ");
		       getNearPeople(false);
			} 
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	
	};
	/**
	 * ��ȡ��������
	 * @param isUpdate
	 * @return
	 */
	private boolean getNearPeople(final boolean isUpdate){
		Log.d("Shake", "�����ȡ��Χ�˵Ĵ���");
		

		userManager.queryKiloMetersListByPage(isUpdate,0,"location", longitude, latitude, true,QUERY_KILOMETERS,"sex",false,new FindListener<User>() {
			//�˷���Ĭ�ϲ�ѯ���д�����λ����Ϣ�����Ա�ΪŮ���û��б�����㲻����������б�Ļ�������ѯ�����е�isShowFriends����Ϊfalse����

				@Override
				public void onSuccess(List<User> arg0) {
					if (CollectionUtils.isNotNull(arg0)) {
						if(isUpdate){
							nears.clear();
						}
						if(arg0.size()<BRequest.QUERY_LIMIT_COUNT){
							
							ShowToast("���������������!");
							nears = arg0;		
							callMapActivity();
							stopLoc();
							finish();
						}else{
							ShowToast("���޸�������!");
						}
					}else{
						ShowToast("���޸�������!");
					}
				}
				
				@Override
				public void onError(int arg0, String arg1) {

					ShowToast("���޸�������!");

				}
			
			});
		

		
		return isSuccess;

	}
	
	private void callMapActivity(){
		Intent intent = new Intent(this,MapPeopleActivity.class);
		startActivity(intent);
	}

}