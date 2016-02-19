
/**
 
 	环信nodeJS调REST示例代码
	--------------------------------------------------
	Copyright(c) 2015 环信即时通信云 www.easemob.com
	--------------------------------------------------
	Author: 神之爱 <fengpei@easemob.com>
	--------------------------------------------------
 */

var easemob=require('./easemob');


var i=0;
switch(i){
	case 10://获取token
		easemob.getToken(function(data){
			//var d=JSON.parse(data);
			console.log(data);

		});
		break;
	case 11://创建单个用户
		easemob.createUser('zhengshuang250','123456');
		break;
	case 12://创建批量用户
		easemob.createUsers([{
			username:'wangwu2',
			password:'123456'
		},{
			username:'zhaoliu2',
			password:'123456'	
		}]);
		break;
	case 13://获取单个用户
		easemob.getUser('fengpei');
		break;
	case 14://获取批量用户
		easemob.getUsers(2);
		break;
	case 15://删除单个用户
		easemob.deleteUser('zhangsan66');
		break;
	case 16://删除批量用户
		easemob.deleteUsers(2);
		break;
	case 17://修改用户昵称
		easemob.editNickname('loveofgod','Loho');
		break;
	case 18://给用户添加好友
		easemob.addFriend('zhangsan','test');
		break;
	case 19://删除用户好友
		easemob.deleteFriend('fengpei','loveofgod');
		break;
	case 20://查看用户好友
		easemob.showFriends('fengpei');
		break;
	case 21://查看用户黑名单
		easemob.getBlacklist('fengpei');
		break;
	case 22://住黑名单中加人
		easemob.addUserForBlacklist('fengpei',['5201314','lisi']);
		break;
	case 23://从黑名单中减人
		easemob.deleteUserFromBlacklist('fengpei','lisi');
		break;
	case 24://查询用户离线消息数
		easemob.getOfflineMessages('fengpei');
		break;
	case 25://查看某条离线消息状态
		easemob.getOfflineMessageStatus('');
		break;
	case 26://禁用用户账号
		easemob.deactiveUser('fengpei');
		break;
	case 27://解禁用户账号
		easemob.activeUser('fengpei');
		break;
	case 28://强制用户下线
		easemob.disconnectUser('fengpei');
		break;
	case 29://发送文本消息
		easemob.sendText({
			type:'users',
			target:['loveofgod','fengpei'],
			content:'this text is from admin',
			from:'admin',
			ext:{a:'a',b:'b',c:'c'}
		});
		break;
	case 30:
		easemob.sendText({
			type:'chatgroups',
			target:['1428032285973','1429894960514'],
			content:'this text is from admin',
			from:'admin',
			ext:{a:'a',b:'b',c:'c'}
		});
		break;
	case 31://发送图片
		easemob.sendImage({
			type:'users',
			target:['fengpei','loveofgod'],
			url:'https://a1.easemob.com/dihon/loveofgod/chatfiles/17b55630-13d5-11e5-9570-e1734d6149fa',
			filename:'dog.jpg',
			secret:'F7VWOhPVEeWrHbXQmsnDQAdyk4NuijHO1CemodZL8WMZRY1u',
			frome:'admin',
			ext:{a:'a',b:'b'}
		});
		break;
	case 32://发送音频
		easemob.sendAudio({
			type:'users',
			target:['fengpei','loveofgod'],
			url:'https://a1.easemob.com/dihon/loveofgod/chatfiles/5b85e240-13d9-11e5-8fd9-5f99858bee3e',
			filename:'song.mp3',
			length:10,
			secret:'W4XiShPZEeWuRP-IXTKBjDyfPk1rVT2mih_sezAxK-ovMTWi',
			frome:'admin',
			ext:{a:'a',b:'b'}
		});
		break;
	case 33://发送视频
		easemob.sendVedio({
			type:'users',
			target:['fengpei','loveofgod'],
			url:'https://a1.easemob.com/dihon/loveofgod/chatfiles/4566d1c0-13db-11e5-83f9-19979a76c06c',
			filename:'web.mp4',
			thumb:'https://a1.easemob.com/dihon/loveofgod/chatfiles/17b55630-13d5-11e5-9570-e1734d6149fa',
			length:10,
			file_length:42190,
			thumb_secret:'F7VWOhPVEeWrHbXQmsnDQAdyk4NuijHO1CemodZL8WMZRY1u',
			secret:'RWbRyhPbEeWIzXHCUTOjP1o8Jae43rbiRTKas0ZqX0WJq0_O',
			frome:'admin',
			ext:{a:'a',b:'b'}
		});
		break;
	case 34://发送透传消息
		easemob.sendCmd({
			type:'users',
			target:['fengpei','loveofgod'],
			action:'this action is from admin',
			from:'admin',
			ext:{a:'a',b:'b'}
		});
		break;
	case 35://获取所有群组
		easemob.getGroups({limit:5});
		break;
	case 36://获取一个或多个群组的详情
		easemob.getGroupDetail(['1445866257312']);
		break;
	case 37://创建一个群组
		easemob.createGroup({
			groupname:'group999',
			desc:'this is a new group created from fengpei',
			public:true,
			maxusers:200,
			approval:true,
			owner:'lisi',
			members:['loveofgod','fengpei','test','zhangsan']	
		});
		break;
	case 38://修改群组信息
		easemob.modifyGroupInfo({
			group_id:'1428032285973',
			groupname:'man',
			description:'update groupinfo',
			maxusers:500	
		});
		break;
	case 39://删除群组
		easemob.deleteGroup('142833155608376');
		break;
	case 40://获取群组中成员
		easemob.getGroupUsers('1428032285973');
		break;
	case 41://群组加人
		easemob.addGroupMember('1444727980278','zhangsan');
		break;
	case 42://群组批量加人
		easemob.addGroupMembers('1428032285973',['zhangsan','love']);
		break;
	case 43://群组减人
		easemob.deleteGroupMember('1428032285973','lisi');
		break;
	case 44://群组批量减人
		easemob.deleteGroupMembers('1428032285973',['peter','loveofgod']);
		break;
	case 45://获取一个用户参与的所有群组
		easemob.getGroupsForUser('fengpei');
		break;
	case 46://群组转让
		easemob.changeGroupOwner({
			group_id:'122633509780062768',
			newowner:'zhangsan'
		});
		break;
	case 47://查询一个群组黑名单中的用户名列表
		easemob.getGroupBlackList('1444727980278');
		break;
	case 48://群组黑名单加人
		easemob.addGroupBlackMember('1444727980278','zhangsan');
		break;
	case 49://群组黑名单减人
		easemob.deleteGroupBlackMember('1444727980278','zhangsan');
		break;
	case 50://上传文件
		easemob.uploadFile({
			filepath:'image/01.jpg',	
		});
		break;
	case 51://下载文件
		easemob.downloadFile({
			uuid:'0490f6e0-1f42-11e5-9559-53a9c7fa95e2',
			header:{'share-secret':'BJD26h9CEeWvx23T1dfQFCS0RTjznvmxvKlmcOIvO9oAAuVC','Accept':'application/octet-stream'}	
		});
		break;
	case 52://下载图片缩略图
		easemob.downloadThumbnail({
			uuid:'0490f6e0-1f42-11e5-9559-53a9c7fa95e2',
			header:{'share-secret':'BJD26h9CEeWvx23T1dfQFCS0RTjznvmxvKlmcOIvO9oAAuVC','Accept':'application/octet-stream','thumbnail':true}	
		});
		break;
	case 53://获取聊天记录
		easemob.getChatRecord({
			ql:'select+*+where+timestamp>1435916105000',
			cursor:'',
			limit:10
		});
		break;
	case 54://创建聊天室
		easemob.createChatRoom({
			name:'test7',
			description:'this chatromm is created by fengpei',
			maxusers:200,
			owner:'xiaoshe',
			members:['xiaoli']	
		});
		break;
	case 55://修改聊天室信息
		easemob.modifyChatRoom({
			chatroom_id:'1434597895697836',
			name:'fengpeichatroom',
			description:'this chatromm is created by fengpei in 2015',
			maxusers:500	
		});
		break;
	case 56://删除聊天室
		easemob.deleteChatRoom('1434597895697836');
		break;
	case 57://获取app中的所有聊天室
		easemob.getChatRooms();
		break;
	case 58://查看聊天室详情
		easemob.getChatRoomDetail('119258558918296112');
		break;
	case 59://获取用户加入的所有聊天室
		easemob.getChatRoomJoined('zhangsan');
		break;
	case 60://聊天室加人
		easemob.addChatRoomMember('116689628932604428','yanhui');
		break;
	case 61://聊天室减人
		easemob.deleteChatRoomMember('116707029891940804','zhangsan');
		break;

}



