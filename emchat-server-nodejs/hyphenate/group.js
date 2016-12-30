var client = require('./../client');
function Group() {

    //Get all groups
    this.getGroups = function getGroups(callback) {
        client.client({
            path: 'chatgroups',
            method: 'GET',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    }

    //Get group(s) detial
    this.getGroupDetail = function (group_ids, callback) {
        client.client({
            path: 'chatgroups/' + group_ids,
            method: 'GET',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    }

    //Create a group
    this.createGroup = function (json, callback) {
        var json = json || {};
        var data = {
            groupname: json.groupname,
            desc: json.desc,
            public: json.public,
            maxusers: json.maxusers,
            approval: json.approval,
            owner: json.owner,
            members: json.members
        };
        client.client({
            data: data,
            path: 'chatgroups',
            method: 'POST',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    }

    //Modify group information
    this.modifyGroupInfo = function (json) {
        var json = json || {};
        var data = {
            groupname: json.groupname,
            description: json.description,
            maxusers: json.maxusers
        };
        client.client({
            data: data,
            path: 'chatgroups/' + json.group_id,
            method: 'PUT',
            callback: function (data) {
                console.log(data);
                typeof json.callback == 'function' && json.callback(data);
            }
        });
    }

    //Delete a group
    this.deleteGroup = function (group_id, callback) {
        client.client({
            path: 'chatgroups/' + group_id,
            method: 'DELETE',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    }

    //Get members of Group
    this.getGroupUsers = function (group_id, callback) {
        client.client({
            path: 'chatgroups/' + group_id + '/users',
            method: 'GET',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    }

    //Add a user to group
    this.addGroupMember = function (groupid, username, callback) {
        client.client({
            path: 'chatgroups/' + groupid + '/users/' + username,
            method: 'POST',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    }

    //Add multiple users to group
    this.addGroupMembers = function (groupid, users, callback) {
        var data = {usernames: users};
        client.client({
            data: data,
            path: 'chatgroups/' + groupid + '/users',
            method: 'POST',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    }

    //Remove a member from group
    this.deleteGroupMember = function (groupid, username, callback) {
        client.client({
            path: '/chatgroups/' + groupid + '/users/' + username,
            method: 'DELETE',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    }

    //Remove multiple members from group
    this.deleteGroupMembers = function (groupid, users, callback) {
        client.client({
            path: 'chatgroups/' + groupid + '/users/' + users,
            method: 'DELETE',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    }

    //Get a list of groups of user joined
    this.getGroupsForUser = function (username, callback) {
        client.client({
            path: 'users/' + username + '/joined_chatgroups',
            method: 'GET',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    }

    //Update group owner
    this.changeGroupOwner = function (json) {
        var json = json || {};
        var data = {
            newowner: json.newowner,
        };
        client.client({
            data: data,
            path: 'chatgroups/' + json.group_id,
            method: 'PUT',
            callback: function (data) {
                console.log(data);
                typeof json.callback == 'function' && json.callback(data);
            }
        });
    }

    //Get group blocked user
    this.getGroupBlackList = function (groupid, callback) {
        client.client({
            path: 'chatgroups/' + groupid + '/blocks/users',
            method: 'GET',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    }

    //Add user to blacklist of group
    this.addGroupBlackMember = function (groupid, username, callback) {
        client.client({
            path: 'chatgroups/' + groupid + '/blocks/users/' + username,
            method: 'POST',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    }

    //Remove user from blacklist of group
    this.deleteGroupBlackMember = function (groupid, username, callback) {
        client.client({
            path: 'chatgroups/' + groupid + '/blocks/users/' + username,
            method: 'DELETE',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        });
    }
}
module.exports = Group;
