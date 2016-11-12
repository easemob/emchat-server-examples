var client = require('./../client');

function SendMessage() {
    //Send text message
    this.sendText = function (json) {
        var json = json || {};
        var data = {
            target_type: json.type,
            target: json.target,
            msg: {type: 'txt', msg: json.content},
            from: json.from
        };
        json.ext && (data.ext = json.ext);
        client.client({
            data: data,
            path: 'messages',
            method: 'POST',
            callback: function (data) {
                console.log(data);
                typeof json.callback == 'function' && json.callback(data);
            }
        });
    }

    //Send image message
    this.sendImage = function (json) {
        var json = json || {};
        var data = {
            target_type: json.type,
            target: json.target,
            msg: {
                type: 'img',
                url: json.url,
                filename: json.filename,
                secret: json.secret,
                size: {width: 480, height: 720}
            },
            from: json.from,
        };
        json.ext && (data.ext = json.ext);
        client.client({
            data: data,
            path: 'messages',
            method: 'POST',
            callback: function (data) {
                console.log(data);
                typeof json.callback == 'function' && json.callback(data);
            }
        });
    }

    //Send audio message
    this.sendAudio = function (json) {
        var json = json || {};
        var data = {
            target_type: json.type,
            target: json.target,
            msg: {type: 'audio', url: json.url, filename: json.filename, length: json.length, secret: json.secret},
            from: json.from,
        };
        json.ext && (data.ext = json.ext);
        client.client({
            data: data,
            path: 'messages',
            method: 'POST',
            callback: function (data) {
                console.log(data);
                typeof json.callback == 'function' && json.callback(data);
            }
        });
    }

    //Send video message
    this.sendVideo = function (json) {
        var json = json || {};
        var data = {
            target_type: json.type,
            target: json.target,
            msg: {
                type: 'video',
                url: json.url,
                filename: json.filename,
                thumb: json.thumb,
                length: json.length,
                file_length: json.file_length,
                thumb_secret: json.thumb_secret,
                secret: json.secret
            },
            from: json.from,
        };
        json.ext && (data.ext = json.ext);
        client.client({
            data: data,
            path: 'messages',
            method: 'POST',
            callback: function (data) {
                console.log(data);
                typeof json.callback == 'function' && json.callback(data);
            }
        });
    }

    //Send commend message
    this.sendCmd = function (json) {
        var json = json || {};
        var data = {
            target_type: json.type,
            target: json.target,
            msg: {type: 'cmd', action: json.action},
            from: json.from,
        };
        json.ext && (data.ext = json.ext);
        client.client({
            data: data,
            path: 'messages',
            method: 'POST',
            callback: function (data) {
                console.log(data);
                typeof json.callback == 'function' && json.callback(data);
            }
        });
    }
}
module.exports = SendMessage;