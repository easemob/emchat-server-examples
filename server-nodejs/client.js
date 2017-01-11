var Token = require('./hyphenate/token');
var config = require('./resources/config');
var request = require('request');
var fs = require('fs');
const util = require('util');

var ORG_NAME = config.org_name;
var APP_NAME = config.app_name;
const HyphenateFullURL =  'https://' + config.host + '/' + ORG_NAME + '/' + APP_NAME;

var Initialized = false;
var token = new Token();

function client(json, callback) {
    if (!Initialized || token.isExpire()) {
        token.accessToken(function () {
            Initialized = true;
            if (typeof callback == 'function') {
                callback(json);
            } else {
                sendRequestWithToken(json);
            }
        });

    } else {
        if (typeof callback == 'function') {
            callback(json);
        } else {
            sendRequestWithToken(json);
        }
    }
}

function uploadFileWithToken(json) {
    if (token == null) {
        console.log('error: failed to access token!')
    } else {
        json.headers = json.headers || {};
        json.headers['http'] = 'multipart/form-data';
        json.headers['Authorization'] = 'Bearer ' + token.getToken();
        request.uploadFile(json);
    }
}

function sendRequestWithToken(options, callback) {
    if (token == null) {
        console.log('error: failed to access token!')
    } else {
        options.headers = options.headers || {};
        options.headers['Authorization'] = 'Bearer ' + token.getToken();
        sendRequest(options, function(error, response, body) {
            if (!error && callback) callback(error, response, body);
            else if (callback) callback(error);
        });
    }
}

function sendRequest(options, callback) {

    var ca = fs.readFileSync(config.ca, 'utf-8');

    options.headers['Content-Type'] = 'application/json';
    options.headers['Accept'] = 'application/json';
    var headers = JSON.stringify(options.headers);

    var data = JSON.stringify(options.data);

    var body = JSON.stringify(options.body);

    var fullOptions = {
        url: HyphenateFullURL + '/' + options.path,
        method: options.method,
        body: body,
        headers: headers,
        data: data
        // ca: [ca],
        // agent: false
    };

    // options.query = {};
    // //connect with query parameters
    // if (json.query != null) {
    //     options.path += '?';
    //     for (var key in json.query) {
    //         if (json.query[key] != null) {
    //             options.path += key + '=' + json.query[key] + '&';
    //         }
    //     }
    //     options.path = options.path.substring(0, options.path.length - 1);
    // }

    console.log('Debugger: options: ' + util.inspect(fullOptions, false, null));

    request(fullOptions, function (error, response, body) {

        console.log('Debugger: new function requestBase. body: ' + util.inspect(body, false, null));
        // console.log('Debugger: new function requestBase. statusCode: ' + util.inspect(response.statusCode, false, null));

        if (callback) callback(error, response, body);
    });
}

module.exports = {
    client: client,
    uploadFileWithToken: uploadFileWithToken,
    sendRequest: sendRequest,
    sendRequestWithToken: sendRequestWithToken
};