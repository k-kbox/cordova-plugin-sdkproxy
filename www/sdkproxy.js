var exec = require('cordova/exec');

exports.init = function (success, error) {
  exec(success, error, 'SdkProxy', 'init', [])
};

exports.wxlogin = function(params, success, error) {
  exec(success, error, 'SdkProxy', 'wxlogin', [params])
}

exports.pay = function (params, success, error) {
  exec(success, error, 'SdkProxy', 'pay', [params])
};

exports.wxpay = function(params, success, error) {
  exec(success, error, 'SdkProxy', 'wxpay', [params])
}

exports.alipay = function(params, success, error) {
  exec(success, error, 'SdkProxy', 'alipay', [params])
}

/*
exports.exit = function (success, error) {
  exec(success, error, 'SdkProxy', 'exit', [])
};

exports.pay = function (id, order, success, error) {
  exec(success, error, 'SdkProxy', 'pay', [id, order])
}*/
