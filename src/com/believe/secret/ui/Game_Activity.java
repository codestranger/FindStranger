package com.believe.secret.ui;

import com.believe.secret.R;
import com.believe.secret.R.layout;
import com.believe.secret.view.pic.GamePintuLayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Game_Activity extends ActivityBase {

	GamePintuLayout mGameView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initTopBarForLeft("╫Бцусно╥");
		mGameView = (GamePintuLayout) findViewById(R.id.id_gameview);
		mGameView.setBitmap(mBitmap);

	}
}
