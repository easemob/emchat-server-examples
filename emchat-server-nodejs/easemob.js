/**
 * Created by Ryan on 2015/5/30.
 */

var https = require('https');

var token = '';
var expire_date = null;
var client_id = 'YOUR_CLIENT_ID';
var client_secret = 'YOUR_CLIENT_SECRET';
var org_name = 'YOUR_ORG_NAME';
var app_name = 'YOUR_APP_NAME';

//通用http请求函数
var http_request = function (data, path, method, callback) {
    data = data || {};
    method = method || 'GET';

    var postData = JSON.stringify(data);
    var options = {
        host: 'a1.easemob.com',
        path: '/' + org_name + '/' + app_name + path,
        method: method,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        }
    };

    var req = https.request(options, function (res) {
        var chunks = [];
        var size = 0;
        res.setEncoding('utf8');
        res.on('data', function (chunk) {
            chunks.push(chunk);
            size += chunk.length;
        });
        res.on('end', function () {
            var data = JSON.parse(Buffer.concat(chunks, size).toString());
            if (callback)
                callback(data);
        });
    });

    req.on('error', function (e) {
        console.log('problem with request: ' + e.message);
    });

    // write data to request body
    req.write(postData);
    req.end();
};

//获取token
var get_token = function (callback) {
    var data = {grant_type: 'client_credentials', client_id: client_id, client_secret: client_secret};
    http_request(data, '/token', 'POST', function (data) {
        token = data.access_token;
        // refresh by current date.
        expire_date = new Date().getTime() + data.expires_in * 1000;
        console.log(data);
        if (callback)
            callback();
    });
};

/**
 * check_token: could be called before every EaseMob REST API call. It'll not 
 * access to EaseMob server, only check the stored expire_date, which is in ms
 * unit.
 */
var check_token = function(callback) { 
    if (new Date().getTime() > expire_date) {
        console.log('Current token expired');
        get_token(callback); // try to get a new token
    } else {
        console.log('Nothing to do');
        callback();
    }
}

//模块初始化调用
get_token();

//注册IM用户[单个]
exports.user_create = function (username, password, callback) {
    // check whether token expires before request.
    check_token(function() {
        var data = {username: username, password: password};
        http_request(data, '/user', 'POST', function (data) {
            if (callback)
                callback(data);
        });
    });
};
//其他API请参照DOCS按上例编写
