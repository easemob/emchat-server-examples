var client = require('./../client');
function ChatRoom() {

    //Create a chat room
    this.createChatRoom = function (json, callback) {
        var data = {
            name: json.name,
            description: json.description,
            maxusers: json.maxusers,
            owner: json.owner,
            members: json.members
        };
        client.client({
            data: data,
            path: 'chatrooms',
            method: 'POST',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    };

    //Update chat room details
    this.modifyChatRoom = function (json, callback) {
        var data = {
            name: json.name,
            description: json.description,
            maxusers: json.maxusers
        };
        client.client({
            data: data,
            path: 'chatrooms/' + json.chatroom_id,
            method: 'PUT',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    };

    //Delete a chat room
    this.deleteChatRoom = function (chatroom_id, callback) {
        client.client({
            path: 'chatrooms/' + chatroom_id,
            method: 'DELETE',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    };

    //Get all chat rooms
    this.getChatRooms = function (callback) {
        client.client({
            path: 'chatrooms',
            method: 'GET',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    };

    //Get chat room detail
    this.getChatRoomDetail = function (chatroom_id, callback) {
        client.client({
            path: 'chatrooms/' + chatroom_id,
            method: 'GET',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    };

    //Get all chat room of user joined
    this.getChatRoomJoined = function (username, callback) {
        client.client({
            path: 'users/' + username + '/joined_chatrooms',
            method: 'GET',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    };
    //Add a member to chat room
    this.addChatRoomMember = function (chatroomid, username, callback) {
        client.client({
            path: 'chatrooms/' + chatroomid + '/users/' + username,
            method: 'POST',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    };
    //Add multiple members to chat room
    this.addChatRoomMembers = function (chatroomid, json, callback) {
        var data = {usernames: json};
        client.client({
            path: 'chatrooms/' + chatroomid + '/users/',
            method: 'POST',
            data: data,
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    };

    //Remove a member from chat room
    this.deleteChatRoomMember = function (chatroomid, username, callback) {
        client.client({
            path: 'chatrooms/' + chatroomid + '/users/' + username,
            method: 'DELETE',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    };
    //Remove multiple member from chat room
    this.deleteChatRoomMembers = function (chatroomid, usernames, callback) {
        client.client({
            path: 'chatrooms/' + chatroomid + '/users/' + usernames,
            method: 'DELETE',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    };
}

module.exports = ChatRoom;