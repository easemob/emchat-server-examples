var client = require('./../client');
function ChatRoom() {

    //创建聊天室
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

    //修改聊天室信息
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

    //删除聊天室
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
    //获取app中所有的聊天室
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

    //获取一个聊天室详情
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
    //获取用户加入的聊天室
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
    //聊天室加人
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
    //批量向聊天室加人
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

    //聊天室减人
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
    //批量从聊天室减人
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