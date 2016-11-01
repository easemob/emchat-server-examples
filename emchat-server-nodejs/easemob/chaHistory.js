var client = require('./../client');
function ChatHistory() {
    //获取聊天记录
    this.getChatMessages = function (ql, limit, cursor, callback) {
        client.getClient.request_with_token({
            path: 'chatmessages',
            method: 'GET',
            query: {'ql':ql, 'limit':limit, 'cursor':cursor},
            headers: {},
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        },client.getClient.request);
    }
}
module.exports = ChatHistory;
