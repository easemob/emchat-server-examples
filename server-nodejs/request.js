var https = require('https');
var config = require('./resources/config');
const util = require('util');
var fs = require('fs');
var fetch = require('node-fetch');

var host = config.host;
var org_name = config.org_name;
var app_name = config.app_name;

exports.httpRequest = function (json) {
    json = json || {};
    json.data = json.data || {};
    json.method = json.method || 'GET';
    json.headers = json.headers || {};
    json.query = json.query || {};

    var postData = JSON.stringify(json.data);
    var ca = fs.readFileSync(config.ca, 'utf-8');
    
    //request parameters
    var options = {
        host: host,
        path: '/' + org_name + '/' + app_name + '/' + json.path,
        method: json.method,
        headers: json.headers,
        ca: [ca],
        agent: false
    };
    
    //connect with query parameters
    if (json.query != null) {
        options.path += '?';
        for (var key in json.query) {
            if (json.query[key] != null) {
                options.path += key + '=' + json.query[key] + '&';
            }
        }
        options.path = options.path.substring(0, options.path.length - 1);
    }
    
    //send request
    console.log('httpRequest: ' + util.inspect(options, false, null));

    var req = https.request(options, function (res) {
        var chunks = '';
        var size = 0;
        res.setEncoding('utf8');
        console.log('------------------------------request--------------------------------');
        console.log('host: ' + options.host + '\n' + 'path: ' + options.path + '\n' + 'method: ' + options.method);

        res.on('data', function (chunk) {
            chunks += chunk;
            size += chunk.length;
        });
        res.on('end', function () {
            //get response data
            //var data = JSON.parse(Buffer.concat(chunks, size).toString());
            console.log('------------------------------response--------------------------------');
            console.log('StatusCode: ' + res.statusCode);
            if (typeof json.callback == 'function')
                json.callback(chunks);
        });
    });
    
    //print error message
    req.on('error', function (e) {
        console.log('problem with request: ' + e.message);
    });

    // write data to request body
    req.write(postData);
    req.end();
};

exports.uploadFile = function (json) {
    json = json || {};
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
};