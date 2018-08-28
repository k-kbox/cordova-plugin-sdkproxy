var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'SdkProxy', 'coolMethod', [arg0]);
};

exports.init = function (success, error) {
  exec(success, error, 'SdkProxy', 'init', [])
};

exports.exit = function (success, error) {
  exec(success, error, 'SdkProxy', 'exit', [])
};

exports.pay = function (id, order, success, error) {
  exec(success, error, 'SdkProxy', 'pay', [id, order])
}