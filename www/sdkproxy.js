var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'SdkProxy', 'coolMethod', [arg0]);
};


exports.init = function (success, error) {
  exec(success, error, 'SdkProxy', 'init', [])
}