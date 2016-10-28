var client = require('./../client');
//var client = new Client();
function User() {
    //注册IM用户[单个]
    this.createUser = function (username, password, callback) {
        var data = {username: username, password: password};
        client.getClient.request_with_token({
            data: data,
            path: 'users',
            method: 'POST',
            headers: {},
            callback: function (data) {
                console.log(data);
                if (typeof callback == 'function')
                    callback(data);
            }
        }, client.getClient.request);
    };
    //注册IM用户[批量]
    this.createUsers = function (users, callback) {
        var data = users;
        client.getClient.request_with_token({
            data: data,
            path: 'users',
            method: 'POST',
            headers:{},
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //获取IM用户[单个]
    this.getUser = function (username, callback) {
        client.getClient.request_with_token({
            path: 'users/' + username,
            method: 'GET',
            headers: {},
            callback: function (data) {
                console.log(data);
                if (typeof callback == 'function')
                    callback(data);
            }
        }, client.getClient.request);
    }

    //获取IM用户[批量]
    this.getUsers = function (limit, cursor, callback) {
        client.getClient.request_with_token({
            path: 'users',
            method: 'GET',
            headers: {},
            query: {'limit': limit, 'cursor':cursor},
            callback: function (data) {
                console.log(data);
                if (typeof callback == 'function')
                    callback(data);
            }
        }, client.getClient.request);
    }

    //删除IM用户[单个]
    this.deleteUser = function (username, callback) {
        client.getClient.request_with_token({
            path: 'users/' + username,
            method: 'DELETE',
            headers: {},
            callback: function (data) {
                console.log(data);
                if (typeof callback == 'function')
                    callback(data);
            }
        }, client.getClient.request);
    }

    //删除IM用户[批量]
    this.deleteUsers = function (limit, cursor, callback) {
        client.getClient.request_with_token({
            path: 'users',
            method: 'DELETE',
            headers: {},
            query: {'limit': limit, 'cursor': cursor},
            callback: function (data) {
                console.log(data);
                if (typeof callback == 'function')
                    callback(data);
            }
        }, client.getClient.request);
    }

    //重置用户密码
    this.resetPassword = function (username, oldpwd, newpwd, callback) {
        var data = {oldpassword: oldpwd, newpassword: newpwd};
        client.getClient.request_with_token({
            data: data,
            path: 'users/' + username + '/password',
            method: 'PUT',
            headers: {},
            callback: function (data) {
                console.log(data);
                if (typeof callback == 'function')
                    callback(data);
            }
        }, client.getClient.request);
    }

    //修改用户昵称
    this.editNickname = function (username, nickname, callback) {
        var data = {nickname: nickname};
        client.getClient.request_with_token({
            data: data,
            path: 'users/' + username,
            method: 'PUT',
            headers: {},
            callback: function (data) {
                console.log(data);
                if (typeof callback == 'function')
                    callback(data);
            }
        }, client.getClient.request);
    }

    //给IM用户添加好友
    this.addFriend = function (username, friendname, callback) {
        client.getClient.request_with_token({
            path: 'users/' + username + '/contacts/users/' + friendname,
            method: 'POST',
            headers: {},
            callback: function (data) {
                console.log(data);
                if (typeof callback == 'function')
                    callback(data);
            }
        }, client.getClient.request);
    }

    //解除IM用户的好友关系
    this.deleteFriend = function (username, friendname, callback) {
        client.getClient.request_with_token({
            path: 'users/' + username + '/contacts/users/' + friendname,
            method: 'DELETE',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //查看用户的好友
    this.showFriends = function (username, callback) {
        client.getClient.request_with_token({
            path: 'users/' + username + '/contacts/users',
            method: 'GET',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //查看用户的黑名单
    this.getBlacklist = function (username, callback) {
        client.getClient.request_with_token({
            path: 'users/' + username + '/blocks/users',
            method: 'GET',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //往黑名单中加人
    this.addUserForBlacklist = function (username, users, callback) {
        var data = {usernames: users};
        client.getClient.request_with_token({
            data: data,
            path: 'users/' + username + '/blocks/users',
            method: 'POST',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //从黑名单中减人
    this.deleteUserFromBlacklist = function (username, blackuser, callback) {
        client.getClient.request_with_token({
            path: 'users/' + username + '/blocks/users/' + blackuser,
            method: 'DELETE',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //查看用户在线状态
    this.isOnline = function (username, callback) {
        client.getClient.request_with_token({
            path: 'users/' + username + '/status',
            method: 'GET',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //查询用户离线消息数
    this.getOfflineMessages = function (username, callback) {
        client.getClient.request_with_token({
            path: 'users/' + username + '/offline_msg_count',
            method: 'GET',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //查询某条离线消息状态
    this.getOfflineMessageStatus = function (username, msgid, callback) {
        client.getClient.request_with_token({
            path: 'users/' + username + '/offline_msg_status/' + msgid,
            method: 'GET',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //禁用用户账号
    this.deactiveUser = function (username, callback) {
        client.getClient.request_with_token({
            path: 'users/' + username + '/deactivate',
            method: 'POST',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //解禁用户账号
    this.activeUser = function (username, callback) {
        client.getClient.request_with_token({
            path: 'users/' + username + '/activate',
            method: 'POST',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);

    }

    //强制用户下线
    this.disconnectUser = function (username, callback) {
        client.getClient.request_with_token({
            path: 'users/' + username + '/disconnect',
            method: 'GET',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }
}
module.exports = User;