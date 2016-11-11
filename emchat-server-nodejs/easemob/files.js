var fs = require('fs');
var FormData = require('form-data');
var client = require('./../client');

function Files() {

    //Upload file
    //Warning: File cannot be over 10MB, otherwise will fail.
    this.uploadFile = function (json, callback) {
        var form = new FormData();
        form.append('file', fs.createReadStream(json.filePath));
        client.getClient.request_with_token({
            path: 'chatfiles',
            method: 'POST',
            form: form,
            headers: {'restrict-access': json.restrictAccess},
            callback: function (data) {
                console.log(data);
                if (typeof callback == 'function')
                    callback(data);
            }
        }, client.getClient.uploadFile_request);
    }

    //Download file
    this.downloadFile = function downloadFile(json, callback) {
        var json = json || {};
        client.getClient.request_with_token({
            path: 'chatfiles/' + json.uuid,
            method: 'GET',
            headers: {'accept': ' application/octet-stream', 'share-secret': json.shareSecret},
            callback: function (data) {
                console.log(data);
                if (typeof callback == 'function')
                    callback(data);
            }
        }, client.getClient.request);
    }

    //Download thumbnail
    this.downloadThumbnail = function downloadThumbnail(json) {
        var json = json || {};
        client.getClient.request_with_token({
            path: 'chatfiles/' + json.uuid,
            method: 'GET',
            headers: {'accept': ' application/octet-stream', 'share-secret': json.shareSecret, 'thumbnail': json.thumbnail},
            callback: function (data) {
                console.log(data);
                typeof json.callback == 'function' && json.callback(data);
            }
        }, client.getClient.request);
    }
}
module.exports = Files;



