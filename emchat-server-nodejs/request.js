var https = require('https');
var config = require('./config/config');
var fs = require('fs');
var fetch = require('node-fetch');

var host = config.host;
var org_name = config.org_name;
var app_name = config.app_name;

exports.http_request = function (json) {
    var json = json || {};
    json.data = json.data || {};
    json.method = json.method || 'GET';
    json.headers = json.headers || {};
    json.query = json.query || {};

    var postData = JSON.stringify(json.data);//从json对象中解析出字符串
    var ca = fs.readFileSync(config.ca, 'utf-8');
    //请求参数
    var options = {
        host: host,
        path: '/' + org_name + '/' + app_name + '/' + json.path,
        method: json.method,
        headers: json.headers,
        ca: [ca],
        agent: false
    };
    //连接query请求
    if (json.query != null) {
        options.path += '?';
        for (var key in json.query) {
            // json.query.forEach(function (o) {
            if (json.query[key] != null) {
                options.path += key + '=' + json.query[key] + '&';
            }
        }
        options.path = options.path.substring(0, options.path.length - 1);
    }
    //发送请求
    var req = https.request(options, function (res) {
        var chunks = '';
        var size = 0;
        res.setEncoding('utf8');//设置返回内容的编码
        console.log('------------------------------request--------------------------------');
        console.log('host: ' + options.host + '\n' + 'path: ' + options.path + '\n' + 'method: ' + options.method);
        //存储返回的响应数据
        res.on('data', function (chunk) {
            chunks += chunk;
            size += chunk.length;
        });
        res.on('end', function () {
            //响应完成，获取完整响应数据
            //var data = JSON.parse(Buffer.concat(chunks, size).toString());
            console.log('------------------------------response--------------------------------');
            console.log('StatusCode: ' + res.statusCode);
            if (typeof json.callback == 'function')
                json.callback(chunks);
        });
    });
    //请求错误时执行的方法
    req.on('error', function (e) {
        console.log('problem with request: ' + e.message);
    });

    // write data to request body
    req.write(postData);//请求体
    req.end();//请求结束
};
exports.uploadFile = function (json) {
    var json = json || {};
    json.data = json.data || {};
    json.method = json.method || 'POST';
    json.headers = json.headers || {};
    json.query = json.query || {};
    json.form = json.form || {};
    var ca = fs.readFileSync(config.ca, 'utf-8');
    console.log('------------------------------request--------------------------------');
    console.log('host: ' + host + '\n' + 'path: ' + json.path + '\n' + 'method: ' + json.method);
    fetch('https://' + host + '/' + org_name + '/' + app_name + '/' + json.path, {
        method: json.method,
        body: json.form,
        headers: json.headers,
        ca: [ca]
    }).then(function (res) {
            console.log('------------------------------response--------------------------------');
            console.log('StatusCode: ' + res.status);
            return res.json();
        }).then(function (json) {
        console.log(json);
    }).catch(function (err) {
        console.log(err);
    });

}