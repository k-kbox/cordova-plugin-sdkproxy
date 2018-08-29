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

    private void init(CallbackContext callbackContext) {
        com.myapp.sdkproxy.SdkProxy.init(cordova.getActivity());
        String appid = com.myapp.sdkproxy.SdkProxy.getAppid();
        String chid = com.myapp.sdkproxy.Sdkproxy.getChid();
        JSONObject json = new JSONObject();
        json.put("appid", appid);
        json.put("chid", chid);
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
