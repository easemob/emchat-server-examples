var client = require('./../client');
function ChatRoom() {

    //Create a chat room
    this.createChatRoom = function (json) {
        var data = {
            name: json.name,
            description: json.description,
            maxusers: json.maxusers,
            owner: json.owner,
            members: json.members
        };
        client.getClient.request_with_token({
            data: data,
            path: '/chatrooms',
            method: 'POST',
            callback: function (data) {
                console.log(data);
                typeof json.callback == 'function' && json.callback(data);
            }
        }, client.getClient.request);
    }

    //Update chat room details
    this.modifyChatRoom = function (json) {
        var data = {
            name: json.name,
            description: json.description,
            maxusers: json.maxusers,
        };
        client.getClient.request_with_token({
            data: data,
            path: '/chatrooms/' + json.chatroom_id,
            method: 'PUT',
            callback: function (data) {
                console.log(data);
                typeof json.callback == 'function' && json.callback(data);
            }
        }, client.getClient.request);
    }

    //Delete a chat room
    this.deleteChatRoom = function (chatroom_id, callback) {
        client.getClient.request_with_token({
            path: '/chatrooms/' + chatroom_id,
            method: 'DELETE',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //Get all chat rooms
    this.getChatRooms = function (callback) {
        client.getClient.request_with_token({
            path: '/chatrooms',
            method: 'GET',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //Get chat room detail
    this.getChatRoomDetail = function (chatroom_id, callback) {
        client.getClient.request_with_token({
            path: '/chatrooms/' + chatroom_id,
            method: 'GET',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //Get all chat room of user joined
    this.getChatRoomJoined = function (username, callback) {
        client.getClient.request_with_token({
            path: '/users/' + username + '/joined_chatrooms',
            method: 'GET',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }
    //Add a member to chat room
    this.addChatRoomMember = function (chatroomid, username, callback) {
        client.getClient.request_with_token({
            path: '/chatrooms/' + chatroomid + '/users/' + username,
            method: 'POST',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }
    //Add multiple members to chat room
    this.addChatRoomMembers = function (chatroomid, json, callback) {
        var data = {usernames: json};
        client.getClient.request_with_token({
            path: '/chatrooms/' + chatroomid + '/users/',
            method: 'POST',
            data: data,
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //Remove a member from chat room
    this.deleteChatRoomMember = function (chatroomid, username, callback) {
        client.getClient.request_with_token({
            path: '/chatrooms/' + chatroomid + '/users/' + username,
            method: 'DELETE',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }
    //Remove multiple member from chat room
    this.deleteChatRoomMembers = function (chatroomid, usernames, callback) {
        client.getClient.request_with_token({
            path: '/chatrooms/' + chatroomid + '/users/' + usernames,
            method: 'DELETE',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }
}

module.exports = ChatRoom;