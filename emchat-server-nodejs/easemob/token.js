var config = require('./../config/config');
var request = require('./../request');
function Token() {
    var client_id = config.client_id;
    var client_secret = config.client_secret;
    var token = '';
    var expiredAt;
    this.setToken = function(myToken){
        token = myToken;
    }
    this.getToken = function () {
        return token;
    }
    this.accessToken = function(callback) {
        var data = {grant_type: 'client_credentials', client_id: client_id, client_secret: client_secret};
        request.http_request({
            data: data,
            path: 'token',
            method: 'POST',
            header: {'Content-Type': 'application/json'},
            callback: function (data) {
                var d = JSON.parse(data);
                token = d.access_token;
                expiredAt = d.expires_in * 1000 + new Date().getMilliseconds();
                //this.setToken(d.access_token);
                //console.log(data);
                //传进来的函数用来接数据
                if (typeof callback == 'function')
                    callback(token);
            }
        });

    };
    //检验token是否过期
    this.isExpire = function(){
        return new Date().getMilliseconds() > expiredAt;
    }
}

module.exports = Token;
