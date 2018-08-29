var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    // exec(success, error, 'SdkProxy', 'coolMethod', [arg0]);
};

exports.init = function (success, error) {
    let ret = {
        appid: '',
      chid: ''
    }
  success(JSON.stringify(ret))
}

