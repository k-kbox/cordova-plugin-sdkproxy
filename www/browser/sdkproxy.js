
exports.init = function (success, error) {
    let ret = {
        appid: '',
      chid: ''
    }
  success(JSON.stringify(ret))
}

exports.wxlogin = function(params, success, error) {
  let ret = {
    code: 0,
    msg: '登录成功',
  }
  success(JSON.stringify(ret))
}

exports.pay = function (params, success, error) {
  let ret = {
    code: 0,
    msg: '支付成功',
  }
  success(JSON.stringify(ret))
};

exports.wxpay = function(params, success, error) {
  let ret = {
    code: 0,
    msg: '支付成功',
  }
  success(JSON.stringify(ret))
}

exports.alipay = function(params, success, error) {
  let ret = {
    code: 0,
    msg: '支付成功',
  }
  success(JSON.stringify(ret))
}
