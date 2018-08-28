package com.j1game.pay;

import android.app.Application;

import com.myapp.sdkproxy.SdkProxy;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		SdkProxy.setLogger(true);
		MyCrashHandler crashHandler = MyCrashHandler.getInstance();
		crashHandler.init(getApplicationContext());
	}
}
