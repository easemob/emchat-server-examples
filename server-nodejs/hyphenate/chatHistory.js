var client = require('./../client');
function ChatHistory() {
    //Get chat history
    this.getChatMessages = function (ql, limit, cursor, callback) {
        client.client({
            path: 'chatmessages',
            method: 'GET',
            query: {'ql':ql, 'limit':limit, 'cursor':cursor},
            headers: {},
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    };
}
module.exports = ChatHistory;
