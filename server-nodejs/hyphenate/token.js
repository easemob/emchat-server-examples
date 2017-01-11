var config = require('../resources/config');
var request = require('request');
const util = require('util');
var fs = require('fs');

var ORG_NAME = config.org_name;
var APP_NAME = config.app_name;
const HyphenateFullURL =  'https://' + config.host + '/' + ORG_NAME + '/' + APP_NAME;

function Token() {

    var client_id = config.client_id;
    var client_secret = config.client_secret;
    var token = '';
    var expiredAt;

    this.getToken = function () {
        return token;
    };

    this.accessToken = function (callback) {

        var ca = fs.readFileSync(config.ca, 'utf-8');
        var path = 'token';

        var body = JSON.stringify({
            grant_type: 'client_credentials',
            client_id: client_id,
            client_secret: client_secret
        });

        var headers = JSON.stringify({
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        });

        var options = {
            url: HyphenateFullURL + '/' + path,
            method: 'POST',
            body: body,
            headers: headers,

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

        console.log('Debugger: options: ' + util.inspect(options, false, null));

        request(options, function (error, response, body) {

            console.log('Debugger: new function requestBase. body: ' + util.inspect(body, false, null));
            // console.log('Debugger: new function requestBase. statusCode: ' + util.inspect(response.statusCode, false, null));

            if (!error && response.statusCode == 200) {
                var d = JSON.parse(body);
                token = d.access_token;
                expiredAt = d.expires_in * 1000 + new Date().getMilliseconds();
                console.log('Debugger: token is: ' + token);
                callback(token);
            }
            else if (error) {
                console.log('Error sending message: ', error);
                callback(null);
            } else if (response.body.error) {
                console.log('Error: ', response.body.error);
                callback(null);
            }
        });
    };

    //Check token is expired
    this.isExpire = function () {
        return new Date().getMilliseconds() > expiredAt;
    };
}

module.exports = Token;
