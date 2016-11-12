var Token = require('./easemob/token');
var request = require('./request');

/*function Client() {
 var Initialized = false;
 var token = new Token();

 this.requestWithToken = function (json, callback) {
 if (!Initialized || token.isExpire()) {
 token.accessToken(function () {
 Initialized = true;
 this.request(json);
 });

 } else {
 this.request(json);
 }

 }

 this.request = function (json) {
 if (token == null) {
 console.log('err: failed to access token!')
 } else {
 json.headers = json.headers || {};
 json.headers['Content-Type'] = 'application/json';
 json.headers['Authorization'] = 'Bearer ' + token.getToken();
 request.httpRequest(json);
 }
 }
 /!*    this.uploadFile_request = function (json) {
 if (token == null) {
 console.log('err: failed to access token!')
 } else {
 json.headers = json.headers || {};
 json.headers['http'] = 'multipart/form-data';
 json.headers['Authorization'] = 'Bearer ' + token.getToken();
 request.uploadFile(json);
 }
 }*!/
 }*/

//exports.getClient = new Client();

var Initialized = false;
var token = new Token();

function client(json, callback) {
    if (!Initialized || token.isExpire()) {
        token.accessToken(function () {
            Initialized = true;
            if (typeof callback == 'function') {
                callback(json);
            }else {
                httpRequestWithToken(json);
            }
        });

    } else {
        if (typeof callback == 'function') {
            callback(json);
        }else {
            httpRequestWithToken(json);
        }
    }

}
function httpRequestWithToken(json) {
    if (token == null) {
        console.log('err: failed to access token!')
    } else {
        json.headers = json.headers || {};
        json.headers['Content-Type'] = 'application/json';
        json.headers['Authorization'] = 'Bearer ' + token.getToken();
        request.httpRequest(json);
    }
}

function uploadFileWithToken(json) {
    if (token == null) {
        console.log('err: failed to access token!')
    } else {
        json.headers = json.headers || {};
        json.headers['http'] = 'multipart/form-data';
        json.headers['Authorization'] = 'Bearer ' + token.getToken();
        request.uploadFile(json);
    }
}
module.exports = {
    client: client,
    httpRequestWithToken: httpRequestWithToken,
    uploadFileWithToken: uploadFileWithToken

}