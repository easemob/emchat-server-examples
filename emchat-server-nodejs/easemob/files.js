var fs = require('fs');
var FormData = require('form-data');
var client = require('./../client');
//上传文件---图片，语音，文件.
//注意:上传文件大小不能超过10M,超过会上传失败
/*
    json = {
        filePath: './resources/image/01.jpg',
        restrictAccess: true
    }
*/
function Files() {
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

//下载文件
/*
    json = {
        uuid: 'ce45e2e0-9b59-11e6-854c-039d301bea0f',
        shareSecret: 'BJD26h9CEeWvx23T1dfQFCS0RTjznvmxvKlmcOIvO9oAAuVC'
    }
    */
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
//下载缩略图
    /*
     json = {
        uuid: 'ce45e2e0-9b59-11e6-854c-039d301bea0f',
        shareSecret: 'BJD26h9CEeWvx23T1dfQFCS0RTjznvmxvKlmcOIvO9oAAuVC',
        thumbnail: true
     }
     */
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



