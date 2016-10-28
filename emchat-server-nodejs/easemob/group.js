var client = require('./../client');
function Group() {

    //获取所有群组
    this.getGroups = function getGroups(callback) {
        client.getClient.request_with_token({
            path: 'chatgroups',
            method: 'GET',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //获取一个或多个群组的详情
    this.getGroupDetail = function (group_ids, callback) {
        client.getClient.request_with_token({
            path: 'chatgroups/' + group_ids,
            method: 'GET',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //创建一个群组
    /*
     json.groupname:
     json.desc:
     json.public: 是否是公开群
     json.maxusers:  最大成员数
     json.approval: 加入公开群是否需要批准
     json.owner:
     json.members:
     json.callback: 回调
     */
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
        client.getClient.request_with_token({
            data: data,
            path: 'chatgroups',
            method: 'POST',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //修改群组信息
    /*
     json.group_id:
     json.groupname:
     json.description:
     json.maxusers:
     json.callback:
     */
    this.modifyGroupInfo = function (json) {
        var json = json || {};
        var data = {
            groupname: json.groupname,
            description: json.description,
            maxusers: json.maxusers
        };
        client.getClient.request_with_token({
            data: data,
            path: 'chatgroups/' + json.group_id,
            method: 'PUT',
            callback: function (data) {
                console.log(data);
                typeof json.callback == 'function' && json.callback(data);
            }
        }, client.getClient.request);
    }

    //删除群组
    this.deleteGroup = function (group_id, callback) {
        client.getClient.request_with_token({
            path: 'chatgroups/' + group_id,
            method: 'DELETE',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //获取群组中成员
    this.getGroupUsers = function (group_id, callback) {
        client.getClient.request_with_token({
            path: 'chatgroups/' + group_id + '/users',
            method: 'GET',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //群组加人
    this.addGroupMember = function (groupid, username, callback) {
        client.getClient.request_with_token({
            path: 'chatgroups/' + groupid + '/users/' + username,
            method: 'POST',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

//群组加人[批量]
    this.addGroupMembers = function (groupid, users, callback) {
        var data = {usernames: users};
        client.getClient.request_with_token({
            data: data,
            path: 'chatgroups/' + groupid + '/users',
            method: 'POST',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //群组减人
    this.deleteGroupMember = function (groupid, username, callback) {
        client.getClient.request_with_token({
            path: '/chatgroups/' + groupid + '/users/' + username,
            method: 'DELETE',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //群组减人[批量]----------不支持该接口
    this.deleteGroupMembers = function (groupid, users, callback) {
        client.getClient.request_with_token({
            path: 'chatgroups/' + groupid + '/users/' + users,
            method: 'DELETE',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //获取一个用户参与的所有群组
    this.getGroupsForUser = function (username, callback) {
        client.getClient.request_with_token({
            path: 'users/' + username + '/joined_chatgroups',
            method: 'GET',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //群组转让
    /*
     json.group_id:
     json.newowner:
     json.callback:
     */
    this.changeGroupOwner = function (json) {
        var json = json || {};
        var data = {
            newowner: json.newowner,
        };
        client.getClient.request_with_token({
            data: data,
            path: 'chatgroups/' + json.group_id,
            method: 'PUT',
            callback: function (data) {
                console.log(data);
                typeof json.callback == 'function' && json.callback(data);
            }
        }, client.getClient.request);
    }

    //查询一个群组黑名单用户名列表
    this.getGroupBlackList = function (groupid, callback) {
        client.getClient.request_with_token({
            path: 'chatgroups/' + groupid + '/blocks/users',
            method: 'GET',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //群组黑名单加人
    this.addGroupBlackMember = function (groupid, username, callback) {
        client.getClient.request_with_token({
            path: 'chatgroups/' + groupid + '/blocks/users/' + username,
            method: 'POST',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }

    //群组黑名单减人
    this.deleteGroupBlackMember = function (groupid, username, callback) {
        client.getClient.request_with_token({
            path: 'chatgroups/' + groupid + '/blocks/users/' + username,
            method: 'DELETE',
            callback: function (data) {
                console.log(data);
                typeof callback == 'function' && callback(data);
            }
        }, client.getClient.request);
    }
}
module.exports = Group;
