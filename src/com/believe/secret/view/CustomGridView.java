package com.believe.secret.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/** �Զ���GridView
  * @ClassName: CustomGridView
  * @Description: TODO
  * @author smile
  * @date 2014-6-17 ����4:28:55
  */
public class CustomGridView extends GridView {

	public CustomGridView(Context context) {
		super(context);
	}
	
	public CustomGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}


	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}


}
