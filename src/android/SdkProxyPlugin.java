package com.cordova.plugins.sdkproxy;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.myapp.sdkproxy.SdkProxy;
import com.myapp.sdkproxy.OnPayListener;

/**
 * This class echoes a string called from JavaScript.
 */
public class SdkProxyPlugin extends CordovaPlugin {
    private static final String TAG = "com.cordova.plugins.sdkproxy.SdkProxy";

    //@Override
    protected void pluginInitialize() {
        super.pluginInitialize();
        Log.d(TAG, "plugin initialized.");
    }

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        // your init code here
        // SdkProxy.init(cordova.getContext().getApplicationContext());

        SdkProxy.init(cordova.getActivity());
        SdkProxy.onCreate(cordova.getActivity());

        Log.d(TAG, "initialized.");
    }

    @Override
    public void onResume(boolean multitasking) {
        Log.d(TAG, "onResume.");
        SdkProxy.onResume(cordova.getActivity());
    }

    @Override
    public void onPause(boolean multitasking) {
        Log.d(TAG, "onPause.");
        SdkProxy.onPause(cordova.getActivity());
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy.");
        SdkProxy.onDestroy();
    }

    @Override
    public void onStop() {
      SdkProxy.onStop();
    }

    @Override
    public void onNewIntent(Intent intent) {
      SdkProxy.onNewIntent(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
      SdkProxy.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("init")) {
            this.init(callbackContext);
            return true;
        }
        if (action.equals("pay")) {
            this.pay(args, callbackContext);
            return true;
        }
        if (action.equals("wxlogin")) {
            this.wxlogin(args, callbackContext);
            return true;
        }
        if (action.equals("wxpay")) {
            this.wxpay(args, callbackContext);
            return true;
        }
        if (action.equals("alipay")) {
            this.alipay(args, callbackContext);
            return true;
        }
        return false;
    }

    private void pay(final JSONArray args, final CallbackContext callbackContext) throws JSONException {
        Log.d(TAG, "pay [args: " + args.toString() + "]");
        cordova.getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                try {
                    JSONObject params = args.getJSONObject(0);
                    SdkProxy.pay(cordova.getActivity(), params.optString("payid", ""), new OnPayListener() {
                        @Override
                        public void onPaySuccess() {
                            callbackContext.success("{\"code\":0,\"msg\":\"支付成功\"}");
                        }

                        @Override
                        public void onPayCanceled() {
                            callbackContext.error("{\"code\":-1,\"msg\":\"取消支付\"}");
                        }

                        @Override
                        public void onPayFailure(int i, String s) {
                            callbackContext.error("{\"code\":" + i + ",\"msg\":\"" + s + "\"}");
                        }
                    });
                }
                catch (Exception e) {
			        e.printStackTrace();
                    callbackContext.error("{\"code\":-1,\"msg\":\"" + e.getMessage() + "\"}");
                }
            }
        });
    }

    private void wxlogin(JSONArray args, CallbackContext callbackContext) {

    }

    private void wxpay(JSONArray args, CallbackContext callbackContext) {

    }

    private void alipay(JSONArray args, CallbackContext callbackContext) {

    }

    private void init(CallbackContext callbackContext) {
        SdkProxy.init(cordova.getActivity());

        String appid = SdkProxy.getAppid();

        String chid = SdkProxy.getChannel();

        String chtype = SdkProxy.getAppInfo(".", "chtype");

        String app_name = this.getAppName(cordova.getActivity());
        String package_name = SdkProxy.getPackageName();

        String version_code = SdkProxy.getAppVersionCode();
        String version_name = SdkProxy.getAppVersionName();

        String td_appid = SdkProxy.getAppInfo(".", "td.appid");
        String wx_appid = SdkProxy.getAppInfo(".", "wx.appid");

        JSONObject json = new JSONObject();
        try {
            json.put("appid", appid);
            json.put("app_name", app_name);
            json.put("chtype", chtype);
            json.put("package_name", package_name);
            json.put("version_code", version_code);
            json.put("version_name", version_name);
            json.put("td_appid", td_appid);
            json.put("wx_appid", wx_appid);
        } catch (Exception e) {
        }
        callbackContext.success(json.toString());
    }

    /**
     * 获取应用程序名称
     */
    private String getAppName(Context context)
    {
        try
        {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
