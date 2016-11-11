var client = require('./../client');
function User() {

    //Create a user
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

    //Create multiple users
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

    //Get a user
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

    //Get users in batch
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

    //Delete a user
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

    //Delete users in batch
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

    //Reset user's password
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

    //Update user's nickname
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

    //Add a friend for user
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

    //Delete a friend for user
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

    //Get user's friends list
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

    //Get user's blacklist
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

    //Block user(s)
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

    //UnBlock user(s)
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

    //Get user online status
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

    //Get offline message count
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

    //Get offline message status
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

    //Deactivate user account
    this.deactivateUser = function (username, callback) {
        client.getClient.request_with_token({
            path: 'users/' + username + '/deactivate',
            method: 'POST',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //Activation user account
    this.activateUser = function (username, callback) {
        client.getClient.request_with_token({
            path: 'users/' + username + '/activate',
            method: 'POST',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);

    }

    //Logout user
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