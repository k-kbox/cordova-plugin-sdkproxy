package cordova.plugins.sdkproxy;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class SdkProxyPlugin extends CordovaPlugin {
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        // your init code here
    }

    @Override
    public void onResume(boolean multitasking) {
        com.myapp.sdkproxy.SdkProxy.onResume(cordova.getActivity());
    }

    @Override
    public void onPause(boolean multitasking) {
        com.myapp.sdkproxy.SdkProxy.onPause(cordova.getActivity());
    }

    @Override
    public void onDestroy() {
        com.myapp.sdkproxy.SdkProxy.onDestroy();
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        if (action.equals("init")) {
            this.init(callbackContext);
            return true;
        }
        return false;
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
        } catch (NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    private void init(CallbackContext callbackContext) {
        com.myapp.sdkproxy.SdkProxy.init(cordova.getActivity());
        String appid = com.myapp.sdkproxy.SdkProxy.getAppid();
        String chid = com.myapp.sdkproxy.SdkProxy.getChannel();

        String app_name = this.getAppName(cordova.getActivity());
        String package_name = com.myapp.sdkproxy.SdkProxy.getPackageName();

        String version_code = com.myapp.sdkproxy.SdkProxy.getAppVersionCode();
        String version_name = com.myapp.sdkproxy.SdkProxy.getAppVersionName();

        String td_appid = com.myapp.sdkproxy.SdkProxy.getAppInfo(".", "talkingdata.appid");
        String wx_appid = com.myapp.sdkproxy.SdkProxy.getAppInfo(".", "wx.appid");

        JSONObject json = new JSONObject();
        try {
            json.put("appid", appid);
            json.put("chid", chid);
            json.put("app_name", app_name);
            json.put("package_name", package_name);
            json.put("version_code", version_code);
            json.put("version_name", version_name);
            json.put("td_appid", td_appid);
            json.put("wx_appid", wx_appid);
        } catch (Exception e) {
        }
        callbackContext.success(json.toString());
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
