var Token = require('./easemob/token');
var request = require('./request');

function Client() {
    var Initialized = false;
    var token = new Token();

    this.request_with_token = function (json, callback) {
        if (!Initialized || token.isExpire()) {
            token.accessToken(function (myToken) {
                token.setToken(myToken);
                console.log('token is: ' + token.getToken());
                Initialized = true;
                callback(json);
            });
        }else{
            callback(json);
        }

    }
    this.request = function (json) {
        if (token == null) {
            console.log('err: failed to access token!')
        } else {
            json.headers = json.headers || {};
            json.headers['Content-Type'] = 'application/json';
            json.headers['Authorization'] = 'Bearer ' + token.getToken();
            request.http_request(json);
        }
    }
    this.uploadFile_request = function (json) {
        if (token == null) {
            console.log('err: failed to access token!')
        } else {
            json.headers = json.headers || {};
            json.headers['http']='multipart/form-data';
            json.headers['Authorization'] = 'Bearer ' + token.getToken();
            request.uploadFile(json);
        }
    }
}
//单例模式
var client;
function getClient(){
    if(!client){
        client = new Client();
    }
    return client;
}
exports.getClient = getClient();
//module.exports = Client;
