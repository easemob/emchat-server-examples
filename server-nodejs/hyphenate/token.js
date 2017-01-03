var config = require('../resources/config');
var request = require('./../request');
function Token() {
    var client_id = config.client_id;
    var client_secret = config.client_secret;
    var token = '';
    var expiredAt;
    this.getToken = function () {
        return token;
    };
    this.accessToken = function(callback) {
        var data = {grant_type: 'client_credentials', client_id: client_id, client_secret: client_secret};
        request.httpRequest({
            data: data,
            path: 'token',
            method: 'POST',
            header: {'Content-Type': 'application/json'},
            callback: function (data) {
                var d = JSON.parse(data);
                token = d.access_token;
                expiredAt = d.expires_in * 1000 + new Date().getMilliseconds();
                console.log('token is: ' + token);
                if (typeof callback == 'function')
                    callback(token);
            }
        });

    };
    //Check token is expired
    this.isExpire = function(){
        return new Date().getMilliseconds() > expiredAt;
    }
}

module.exports = Token;
