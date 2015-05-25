package com.believe.secret.ui;
/**
 * ҡһҡҳ�棬ͨ��ҡ���ֻ������ͼ����ʾ����������Ϣ��
 */
import com.believe.secret.R;

import android.app.Activity;
import android.content.Context;
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
import android.widget.Button;
import android.widget.Toast;
public class ShakeFindFriendActivity extends BaseActivity{
	private SensorManager sensorManager;
	private Vibrator vibrator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shake_findfriend);
		sensorManager = (SensorManager) getSystemService (Context.SENSOR_SERVICE);
		Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(listener, sensor, SensorManager. SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (sensorManager != null) {
			sensorManager.unregisterListener(listener);
		}
	}

	private SensorEventListener listener = new SensorEventListener() {

		@Override
		public void onSensorChanged(SensorEvent event) {
			// ���ٶȿ��ܻ��Ǹ�ֵ������Ҫȡ���ǵľ���ֵ
			float xValue = Math.abs(event.values[0]);
			float yValue = Math.abs(event.values[1]);
			float zValue = Math.abs(event.values[2]);
			if (xValue > 15 || yValue > 15 || zValue > 15) {
				// ��Ϊ�û�ҡ�����ֻ�������ҡһҡ�߼�
			//	Toast.makeText(ShakeFindFriendActivity.this, "ҡһҡ", Toast.LENGTH_SHORT).show();
		        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		       // long[] pattern = {0, 500, 500, 1000}; // OFF/ON /OFF/ON......                
		       // vibrator.vibrate(pattern, -1); 
		       vibrator.vibrate(new long[]{500,300,500,300}, -1);
			}
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	
	};

}