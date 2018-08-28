package com.j1game.pay;

import com.myapp.sdkproxy.PayOrder;
import com.myapp.sdkproxy.SdkProxy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

public class MyPay {

	/*
	 * 初始化
	 */
	public static void init(final Activity activity) {
		MyPay.activity = activity;
		SdkProxy.setAppInfo("provider", "demo");
	}
	
	public static void onPause(final Activity activity) {
		
	}

	public static void onResume(final Activity activity) {
		
	}

	public static void onDestroy(final Activity activity) {
		
	}
	
	public static void onDestroy() {
		
	}

	/**
	 * 是否开启声音
	 * @param activity
	 * @return 默认为null, 由用户自己管理音效开关
	 */
	public static Boolean isMusicEnable(final Activity activity) {
		return null;
	}

	/**
	 * 更多游戏
	 * @param activity
	 */
	public static void onMoreGame(final Activity activity) {
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				Toast.makeText(activity, "更多游戏", Toast.LENGTH_SHORT).show();
			}
		});
	}

	/**
	 * 退出游戏
	 * @param activity
	 * @param
	 */
	public static void onExit(final Activity activity, final MyListener listener) {
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				AlertDialog.Builder builder = new AlertDialog.Builder(activity);
				builder.setTitle("提示");
				builder.setMessage("是否退出游戏？");
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						listener.onExitCancel();
					}
				});
				builder.setPositiveButton("是的", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						listener.onExitConfirm();
					}
				});
				builder.show();
			}
		});
	}

	
	/**
	 * 
	 * @param activity
	 * @param id          计费点序号
	 * @param
	 */
	public static void pay(final Activity activity, final String id, 
			final PayOrder order, final MyListener listener) {
		// do pay
		if (!running) {
//			running = true;
			System.out.println("payId " + id);
//			MyPay.listener = listener;
			////////////////////////////
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setTitle("测试");
			builder.setMessage("商品：" + order.getName() + "\n" +
					"价格：" + order.getAmount() + "\n" +
					"说明：" + order.getDesc() + "\n\n" +
					"测试中，不实际支付，请直接选择结果？");
			builder.setNegativeButton("失败", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					listener.onPayFailure("test", "failed");
				}
			});
			builder.setPositiveButton("成功", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					listener.onPaySuccess();
				}
			});
			builder.show();
		}
		else {

		}

	}

	private static Activity activity;
	private static PayOrder order;
	private static MyListener listener;

	private static boolean running = false;
	
}
