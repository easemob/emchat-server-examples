/**
 
 	环信nodeJS调REST示例代码
	--------------------------------------------------
	Copyright(c) 2015 环信即时通信云 www.easemob.com
	--------------------------------------------------
	Author: 神之爱 <fengpei@easemob.com>
	--------------------------------------------------
 */

var https = require('https');

var token='YOUR_TOKEN';
var client_id = 'YOUR_CLIENT_ID';
var client_secret = 'YOUR_CLIENT_SECRET';

//通用http请求函数
/*
	json.data: 请求参数
	json.path: 路径
	json.method: 请求方式
	json.headers: 请求头
	json.callback: 回调函数
*/
var http_request = function (json) {
	var json = json || {};
    json.data = json.data || {};
    json.method = json.method || 'GET';
	json.headers = json.headers || {};
	json.headers['Content-Type']='application/json';
	//json.headers['http']='multipart/form-data';
	json.headers['Authorization']='Bearer '+token;
	
    var postData = JSON.stringify(json.data);//从json对象中解析出字符串
    //请求参数
    var options = {
        host: 'a1.easemob.com',
        path: '/org_name/app_name' + json.path,
        method: json.method,
        headers:json.headers
    };
    //发送请求
    var req = https.request(options, function (res) {
        var chunks = '';
        var size = 0;
        res.setEncoding('utf8');//设置返回内容的编码
		//存储返回的响应数据
        res.on('data', function (chunk) {
            chunks+=chunk;
            size += chunk.length;
        });
        res.on('end', function () {
			//响应完成，获取完整响应数据
            //var data = JSON.parse(Buffer.concat(chunks, size).toString());
            if (typeof json.callback=='function')
                json.callback(chunks);
        });
    });
	//请求错误时执行的方法
    req.on('error', function (e) {
        console.log('problem with request: ' + e.message);
    });

    // write data to request body
    req.write(postData);//请求体
    req.end();//请求结束
};


//---------------------------------------------------用户体系集成


//获取token
function getToken(callback) {
    var data = {grant_type: 'client_credentials', client_id: client_id, client_secret: client_secret};
	http_request({
		data:data,
		path:'/token',
		method:'POST',
		callback:function (data) {
			var d=JSON.parse(data);
			var token = d.access_token;
			//console.log(data);
			//传进来的函数用来接数据
			if(typeof callback == 'function')
				callback(token);
			
		}	
	});
	
};



//注册IM用户[单个]
function createUser(username, password, callback) {
    var data = {username: username, password: password};
	http_request({
		data:data,
		path:'/users',
		method:'POST',
		callback:function(data){
			console.log(data);
			if(typeof callback == 'function')
				callback(data);
		}	
	});
};


//注册IM用户[批量]
function createUsers(users,callback){
	var data=users;
	http_request({
		data:data,
		path:'/users',
		method:'POST',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		} 
	});
}
//重置用户密码
function resetPassword(username,oldpwd,newpwd,callback){
	var data={oldpassword:oldpwd,newpassword:newpwd};
	http_request({
		data:data,
		path:'/users/'+username+'/password',
		method:'PUT',
		callback:function(data){
			console.log(data);
			if(typeof callback == 'function')
				callback(data);	
		}	
	});
}

//获取IM用户[单个]
function getUser(username,callback){
	http_request({
		path:'/users/'+username,
		method:'GET',
		callback:function(data){
			console.log(data);
			if(typeof callback == 'function')
				callback(data);	
		}	
	});
}

//获取IM用户[批量]
function getUsers(limit,callback){
	http_request({
		path:'/users?limit='+limit,
		method:'GET',
		callback:function(data){
			console.log(data);
			if(typeof callback == 'function')
				callback(data);	
		}	
	});
}

//删除IM用户[单个]
function deleteUser(username,callback){
	http_request({
		path:'/users/'+username,
		method:'DELETE',
		callback:function(data){
			console.log(data);
			if(typeof callback == 'function')
				callback(data);	
		}	
	});
}

//删除IM用户[批量]
function deleteUsers(limit,callback){
	http_request({
		path:'/users?limit='+limit,
		method:'DELETE',
		callback:function(data){
			console.log(data);
			if(typeof callback == 'function')
				callback(data);	
		}	
	});
}

//修改用户昵称
function editNickname(username,nickname,callback){
	var data={nickname:nickname};
	http_request({
		data:data,
		path:'/users/'+username,
		method:'PUT',
		callback:function(data){
			console.log(data);
			if(typeof callback == 'function')
				callback(data);	
		}	
	});
}
//给IM用户添加好友
function addFriend(username,friendname,callback){
	http_request({
		path:'/users/'+username+'/contacts/users/'+friendname,
		method:'POST',
		callback:function(data){
			console.log(data);
			if(typeof callback == 'function')
				callback(data);	
		}	
	});
}

//解除IM用户的好友关系
function deleteFriend(username,friendname,callback){
	http_request({
		path:'/users/'+username+'/contacts/users/'+friendname,
		method:'DELETE',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}	
	});
}
//查看用户的好友
function showFriends(username,callback){
	http_request({
		path:'/users/'+username+'/contacts/users',
		method:'GET',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);
		}
		
	});
}
//查看用户的黑名单
function getBlacklist(username,callback){
	http_request({
		path:'/users/'+username+'/blocks/users',
		method:'GET',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}	
	});
}
//往黑名单中加人
function addUserForBlacklist(username,users,callback){
	var data={usernames:users};
	http_request({
		data:data,
		path:'/users/'+username+'/blocks/users',
		method:'POST',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}	
	});
}

//从黑名单中减人
function deleteUserFromBlacklist(username,blackuser,callback){
	http_request({
		path:'/users/'+username+'/blocks/users/'+blackuser,
		method:'DELETE',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}	
	});
}
//查看用户在线状态
function isOnline(username,callback){
	http_request({
		path:'/users/'+username+'/status',
		method:'GET',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);
		}	
	});
}
//查询用户离线消息数
function getOfflineMessages(username,callback){
	http_request({
		path:'/users/'+username+'/offline_msg_count',
		method:'GET',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}	
	});
}
//查询某条离线消息状态-----暂未测试---格式："{消息id}":"{状态}", 状态的值有两个: deliverd 表示此用户的该条离线消息已经收到过了，undelivered 表示此用户的该条离线消息未还未收到
   // },
function getOfflineMessageStatus(username,msgid,callback){
	http_request({
		path:'/users/'+username+'/offline_msg_status/'+msgid,	
		method:'GET',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}
	});
}
//禁用用户账号
function deactiveUser(username,callback){
	http_request({
		path:'/users/'+username+'/deactivate',
		method:'POST',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}	
	});
		
}
//解禁用户账号
function activeUser(username,callback){
	http_request({
		path:'/users/'+username+'/activate',
		method:'POST',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}	
	});
		
}
//强制用户下线
function disconnectUser(username,callback){
	http_request({
		path:'/users/'+username+'/disconnect',
		method:'GET',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}	
	});
		
}

//----------------------------------------------------发送消息

//发送文本消息
/*
	json.type:
	json.target:
	json.content:
	json.from:
	json.ext:
	json.callback:
	
*/
function sendText(json){
	var json = json || {};
	var data={
		target_type:json.type,
		target:json.target,
		msg:{type:'txt',msg:json.content},
		from:json.from,
	};
	json.ext && (data.ext=json.ext);
	http_request({
		data:data,
		path:'/messages',
		method:'POST',
		callback:function(data){
			console.log(data);
			typeof json.callback == 'function' && json.callback(data);
		}	
	});
}

//发送图片消息
/*
	json.type:
	json.target:
	json.url:
	json.filename:
	json.secret:
	json.from:
	json.ext:
	json.callback:
	
*/
function sendImage(json){
	var json = json || {};
	var data={
		target_type:json.type,
		target:json.target,
		msg:{type:'img',url:json.url,filename:json.filename,secret:json.secret,size:{width:480,height:720}},
		from:json.from,
	};
	json.ext && (data.ext=json.ext);
	http_request({
		data:data,
		path:'/messages',
		method:'POST',
		callback:function(data){
			console.log(data);
			typeof json.callback == 'function' && json.callback(data);
		}	
	});
}

//发送语音消息
/*
	json.type:
	json.target:
	json.url:
	json.filename:
	json.length:
	json.secret:
	json.from:
	json.ext:
	json.callback:
	
*/
function sendAudio(json){
	var json = json || {};
	var data={
		target_type:json.type,
		target:json.target,
		msg:{type:'audio',url:json.url,filename:json.filename,length:json.length,secret:json.secret},
		from:json.from,
	};
	json.ext && (data.ext=json.ext);
	http_request({
		data:data,
		path:'/messages',
		method:'POST',
		callback:function(data){
			console.log(data);
			typeof json.callback == 'function' && json.callback(data);
		}	
	});
}

//发送视频消息
/*
	json.type:
	json.target:
	json.url:
	json.filename:
	json.thumb:
	json.length:
	json.file_length:
	json.thumb_secret:
	json.secret:
	json.from:
	json.ext:
	json.callback:
	
*/
function sendVedio(json){
	var json = json || {};
	var data={
		target_type:json.type,
		target:json.target,
		msg:{type:'video',url:json.url,filename:json.filename,thumb:json.thumb,length:json.length,file_length:json.file_length,thumb_secret:json.thumb_secret,secret:json.secret},
		from:json.from,
	};
	json.ext && (data.ext=json.ext);
	http_request({
		data:data,
		path:'/messages',
		method:'POST',
		callback:function(data){
			console.log(data);
			typeof json.callback == 'function' && json.callback(data);
		}	
	});
}

//发送透传消息
/*
	json.type:
	json.target:
	json.action:
	json.from:
	json.ext:
	json.callback:
	
*/
function sendCmd(json){
	var json = json || {};
	var data={
		target_type:json.type,
		target:json.target,
		msg:{type:'cmd',action:json.action},
		from:json.from,
	};
	json.ext && (data.ext=json.ext);
	http_request({
		data:data,
		path:'/messages',
		method:'POST',
		callback:function(data){
			console.log(data);
			typeof json.callback == 'function' && json.callback(data);
		}	
	});
}

//---------------------------------------------------------------群组操作

//获取所有群组
function getGroups(json){
	var json=json||{};
	json.limit=json.limit || 0;
	http_request({
		path:'/chatgroups?limit='+json.limit,
		method:'GET',
		callback:function(data){
			console.log(data);
			typeof json.callback == 'function' && json.callback(data);	
		}	
	});
}
//获取一个或多个群组的详情
function getGroupDetail(group_ids,callback){
	http_request({
		path:'/chatgroups/'+group_ids,
		method:'GET',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}	
	});
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
function createGroup(json){
	var json = json || {};
	var data={
		groupname:json.groupname,	
		desc:json.desc,
		public:json.public,
		maxusers:json.maxusers,
		approval:json.approval,
		owner:json.owner,
		members:json.members
	};
	http_request({
		data:data,
		path:'/chatgroups',
		method:'POST',
		callback:function(data){
			console.log(data);
			typeof json.callback == 'function' && json.callback(data);	
		}
	});
}
//修改群组信息
/*
	json.group_id:
	json.groupname:
	json.description:
	json.maxusers:
	json.callback:
*/
function modifyGroupInfo(json){
	var json = json || {};
	var data={
		groupname:json.groupname,
		description:json.description,
		maxusers:json.maxusers	
	};
	http_request({
		data:data,
		path:'/chatgroups/'+json.group_id,
		method:'PUT',
		callback:function(data){
			console.log(data);
			typeof json.callback == 'function' && json.callback(data);	
		}	
	});
}
//删除群组
function deleteGroup(group_id,callback){
	http_request({
		path:'/chatgroups/'+group_id,
		method:'DELETE',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}	
	});
}
//获取群组中成员
function getGroupUsers(group_id,callback){
	http_request({
		path:'/chatgroups/'+group_id+'/users',
		method:'GET',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}	
	});
}
//群组加人
function addGroupMember(groupid,username,callback){
	http_request({
		path:'/chatgroups/'+groupid+'/users/'+username,
		method:'POST',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}	
	});
}

//群组加人[批量]
function addGroupMembers(groupid,users,callback){
	var data={usernames:users};
	http_request({
		data:data,
		path:'/chatgroups/'+groupid+'/users',
		method:'POST',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}	
	});
}
//群组减人
function deleteGroupMember(groupid,username,callback){
	http_request({
		path:'/chatgroups/'+groupid+'/users/'+username,	
		method:'DELETE',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}
	});
}
//群组减人[批量]----------不支持该接口
function deleteGroupMembers(groupid,users,callback){
	http_request({
		path:'/chatgroups/'+groupid+'/users/'+users,	
		method:'DELETE',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}
	});
}
//获取一个用户参与的所有群组
function getGroupsForUser(username,callback){
	http_request({
		path:'/users/'+username+'/joined_chatgroups',
		method:'GET',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}	
	});
}

//群组转让
/*
	json.group_id:
	json.newowner:
	json.callback:
*/
function changeGroupOwner(json){
	var json = json || {};
	var data={
		newowner:json.newowner,
	};
	http_request({
		data:data,
		path:'/chatgroups/'+json.group_id,
		method:'PUT',
		callback:function(data){
			console.log(data);
			typeof json.callback == 'function' && json.callback(data);	
		}	
	});
}
//查询一个群组黑名单用户名列表
function getGroupBlackList(groupid,callback){
	http_request({
		path:'/chatgroups/'+groupid+'/blocks/users',
		method:'GET',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}	
	});
}

//群组黑名单加人
function addGroupBlackMember(groupid,username,callback){
	http_request({
		path:'/chatgroups/'+groupid+'/blocks/users/'+username,
		method:'POST',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}	
	});
}

//群组黑名单减人
function deleteGroupBlackMember(groupid,username,callback){
	http_request({
		path:'/chatgroups/'+groupid+'/blocks/users/'+username,
		method:'DELETE',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}	
	});
}

//--------------------------------------------文件上传下载
//上传文件---图片，语音，文件.
//注意:上传文件大小不能超过10M,超过会上传失败
/*
	json.file:
	
	json.header:头部，是否限制访问权限
	json.callback:
*/
function uploadFile(json){
	var json = json || {};
	var data={
		file:json.file
	};
	http_request({
		data:data,
		path:'/chatfiles',
		method:'POST',
		headers:json.header,
		callback:function(data){
			console.log(data);
			typeof json.callback == 'function' && json.callback(data);
		}	
	});
}
//下载文件
/*
	json.uuid:
	json.header:
	json.callback:	

*/
function downloadFile(json){
	var json = json || {};
	http_request({
		path:'/chatfiles/'+json.uuid,
		method:'GET',
		headers:json.header,
		callback:function(data){
			console.log(data);
			typeof json.callback == 'function' && json.callback(data);
		}	
	});
}
//下载文件
/*
	json.uuid:
	json.header:
	json.callback:	

*/
function downloadThumbnail(json){
	var json = json || {};
	http_request({
		path:'/chatfiles/'+json.uuid,
		method:'GET',
		headers:json.header,
		callback:function(data){
			console.log(data);
			typeof json.callback == 'function' && json.callback(data);
		}	
	});
}


//---------------------------------------------导出聊天记录

//取聊天记录
/*
	json.ql: 查询条件，如：$ql= "select+*+where+time>1403143434443'" . $uid . "'+or+to='". $uid ."'+order+by+timestamp+desc&limit=" . $limit . $cursor;
	json.cursor: 分页参数
	json.limit: 条数，默认20
*/
function getChatRecord(json){
	http_request({
		path:'/chatmessages?ql='+json.ql+'&limit='+json.limit+'&cursor='+json.cursor,
		method:'GET',
		callback:function(data){
			console.log(data);
			typeof json.callback == 'function' && json.callback(data);	
		}	
	});
}


//-----------------------------------------------------聊天室操作

//创建聊天室
/*
	json.name:  *
	json.description:  *
	json.maxusers:
	json.owner:  *
	json.members:
	
	json.callback:
*/
function createChatRoom(json){
	var data={
		name:json.name,
		description:json.description,
		maxusers:json.maxusers,
		owner:json.owner,
		members:json.members	
	};	
	http_request({
		data:data,
		path:'/chatrooms',
		method:'POST',
		callback:function(data){
			console.log(data);
			typeof json.callback == 'function' && json.callback(data);	
		}
	});
}

//修改聊天室信息
/*
	json.chatroom_id:
	json.name:  *
	json.description:  *
	json.maxusers:
	

	json.callback:
*/
function modifyChatRoom(json){
	var data={
		name:json.name,
		description:json.description,
		maxusers:json.maxusers,	
	};	
	http_request({
		data:data,
		path:'/chatrooms/'+json.chatroom_id,
		method:'PUT',
		callback:function(data){
			console.log(data);
			typeof json.callback == 'function' && json.callback(data);	
		}
	});
}

//删除聊天室
function deleteChatRoom(chatroom_id,callback){
	http_request({
		path:'/chatrooms/'+chatroom_id,
		method:'DELETE',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}	
	});
}
//获取app中所有的聊天室
function getChatRooms(callback){
	http_request({
		path:'/chatrooms',
		method:'GET',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}	
	});
}

//获取一个聊天室详情
function getChatRoomDetail(chatroom_id,callback){
	http_request({
		path:'/chatrooms/'+chatroom_id,
		method:'GET',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}	
	});
}
//获取用户加入的聊天室
function getChatRoomJoined(username,callback){
	http_request({
		path:'/users/'+username+'/joined_chatrooms',
		method:'GET',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}	
	});
}
//聊天室加人
function addChatRoomMember(chatroomid,username,callback){
	http_request({
		path:'/chatrooms/'+chatroomid+'/users/'+username,
		method:'POST',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}	
	});
}

//聊天室减人
function deleteChatRoomMember(chatroomid,username,callback){
	http_request({
		path:'/chatrooms/'+chatroomid+'/users/'+username,
		method:'DELETE',
		callback:function(data){
			console.log(data);
			typeof callback == 'function' && callback(data);	
		}	
	});
}



//模块初始化调用

exports.getToken=getToken;
exports.createUser=createUser;
exports.createUsers=createUsers;
exports.resetPassword=resetPassword;
exports.getUser=getUser;
exports.getUsers=getUsers;
exports.deleteUser=deleteUser;
exports.deleteUsers=deleteUsers;
exports.editNickname=editNickname;
exports.addFriend=addFriend;
exports.deleteFriend=deleteFriend;
exports.showFriends=showFriends;
exports.getBlacklist=getBlacklist;
exports.addUserForBlacklist=addUserForBlacklist;
exports.deleteUserFromBlacklist=deleteUserFromBlacklist;
exports.isOnline=isOnline;
exports.getOfflineMessages=getOfflineMessages;
exports.getOfflineMessageStatus=getOfflineMessageStatus;
exports.deactiveUser=deactiveUser;
exports.activeUser=activeUser;
exports.disconnectUser=disconnectUser;


exports.sendText=sendText;
exports.sendImage=sendImage;
exports.sendAudio=sendAudio;
exports.sendVedio=sendVedio;
exports.sendCmd=sendCmd;

exports.getGroups=getGroups;
exports.getGroupDetail=getGroupDetail;
exports.createGroup=createGroup;
exports.modifyGroupInfo=modifyGroupInfo;
exports.deleteGroup=deleteGroup;
exports.getGroupUsers=getGroupUsers;
exports.addGroupMember=addGroupMember;
exports.addGroupMembers=addGroupMembers;
exports.deleteGroupMember=deleteGroupMember;
//exports.deleteGroupMembers=deleteGroupMembers;
exports.getGroupsForUser=getGroupsForUser;
exports.changeGroupOwner=changeGroupOwner;
exports.getGroupBlackList=getGroupBlackList;
exports.addGroupBlackMember=addGroupBlackMember;
exports.deleteGroupBlackMember=deleteGroupBlackMember;

exports.uploadFile=uploadFile;
exports.downloadFile=downloadFile;
exports.downloadThumbnail=downloadThumbnail;

exports.getChatRecord=getChatRecord;

exports.createChatRoom=createChatRoom;
exports.modifyChatRoom=modifyChatRoom;
exports.deleteChatRoom=deleteChatRoom;
exports.getChatRooms=getChatRooms;
exports.getChatRoomDetail=getChatRoomDetail;
exports.getChatRoomJoined=getChatRoomJoined;
exports.addChatRoomMember=addChatRoomMember;
exports.deleteChatRoomMember=deleteChatRoomMember;






