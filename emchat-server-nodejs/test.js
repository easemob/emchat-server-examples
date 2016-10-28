/**

 环信nodeJS调REST示例代码
 --------------------------------------------------
 Copyright(c) 2015 环信即时通信云 www.easemob.com
 --------------------------------------------------
 Author: 神之爱 <fengpei@easemob.com>
 --------------------------------------------------
 */
var async = require('async');
var Token = require('./easemob/token');
var User = require('./easemob/user');
var ChatHistory = require('./easemob/chaHistory');
var Files = require('./easemob/files');
var Group = require('./easemob/group');
var ChatRoom = require('./easemob/chatRoom');
var SendMessage = require('./easemob/sendMessage');
token = new Token();
user = new User();
chatHistory = new ChatHistory();
files = new Files();
group = new Group();
chatRoom = new ChatRoom();
sendMessage = new SendMessage();
var i = 65;
switch (i) {
    case 10://获取token
        token.accessToken(function (data) {
            console.log(data);
        });
        break;
    case 11://创建单个用户
        user.createUser('user100', '123456');
        break;
    case 12://创建批量用户
        user.createUsers([{
            username: 'user101',
            password: '123456'
        }, {
            username: 'user102',
            password: '123456'
        }, {
            username: 'user103',
            password: '123456'
        }]);
        break;
    case 13://获取单个用户
        user.getUser('user100');
        break;
    case 14://获取批量用户
        user.getUsers(5);
        break;
    case 15://删除单个用户
        user.deleteUser('user100');
        break;
    case 16://删除批量用户
        user.deleteUsers(2, '${cursor}');
        break;
    case 17: //重置用户密码
        user.resetPassword('user101', '123456', '654321');
        break;
    case 18://修改用户昵称
        user.editNickname('user101', 'Aily');
        break;
    case 19://给用户添加好友
        user.addFriend('user101', 'user102');
        break;
    case 20://删除用户好友
        user.deleteFriend('user101', 'user102');
        break;
    case 21://查看用户好友
        user.showFriends('user101');
        break;
    case 22://查看用户黑名单
        user.getBlacklist('user101');
        break;
    case 23://住黑名单中加人
        user.addUserForBlacklist('user101', ['user102', 'user103']);
        break;
    case 24://从黑名单中减人
        user.deleteUserFromBlacklist('user101', 'user102');
        break;
    case 25://查询用户在线状态
        user.isOnline('user101');
        break;
    case 26://查询用户离线消息数
        user.getOfflineMessages('user101');
        break;
    case 27://查看某条离线消息状态
        user.getOfflineMessageStatus('user101', '${msgid}');
        break;
    case 28://禁用用户账号
        user.deactiveUser('user101');
        break;
    case 29://解禁用户账号
        user.activeUser('user101');
        break;
    case 30://强制用户下线
        user.disconnectUser('user101');
        break;
    case 31://获取聊天记录
        chatHistory.getChatMessages('${ql}','${cursor}');
        break;
    case 32://发送文本消息
        sendMessage.sendText({
            type: 'users',
            target: ['user101'],
            content: 'this text is from admin',
            from: 'admin',
            ext: {a: 'a', b: 'b', c: 'c'}
        });
        break;
    case 33://发送图片
        sendMessage.sendImage({
            type: 'users',
            target: ['user101', 'user102'],
            url: 'https://a1.easemob.com/dihon/loveofgod/chatfiles/61611e30-9b5c-11e6-b3d9-9d52b6f6416b',
            filename: 'dog.jpg',
            secret: 'YWEeOptcEeaG8D-LXrAmEykZ07q6Q_d5jLK49nlbDAc7s3Yc',
            frome: 'admin',
            ext: {a: 'a', b: 'b'}
        });
        break;
    case 34://发送音频
        sendMessage.sendAudio({
            type: 'users',
            target: ['user101', 'user102'],
            url: 'https://a1.easemob.com/dihon/loveofgod/chatfiles/83419440-9b79-11e6-a408-01f417d892b0',
            filename: 'song.mp3',
            length: 10,
            secret: 'g0GUSpt5Eea-E78LqsqYRGrpCneE0xwEXPN8uvq327wtIiJ2',
            frome: 'admin',
            ext: {a: 'a', b: 'b'}
        });
        break;
    case 35://发送视频
        sendMessage.sendVedio({
            type: 'users',
            target: ['user101', 'user102'],
            url: 'https://a1.easemob.com/dihon/loveofgod/chatfiles/a4eaacd0-9b79-11e6-992f-b32958bd06ae',
            filename: 'web.mp4',
            thumb: 'https://a1.easemob.com/dihon/loveofgod/chatfiles/17b55630-13d5-11e5-9570-e1734d6149fa',
            length: 10,
            file_length: 42190,
            thumb_secret: 'F7VWOhPVEeWrHbXQmsnDQAdyk4NuijHO1CemodZL8WMZRY1u',
            secret: 'pOqs2pt5EeaLsPXcXfAk0UrWaF5EEV9SNsCwxhMmT-ClN0PG',
            frome: 'admin',
            ext: {a: 'a', b: 'b'}
        });
        break;
    case 36://发送透传消息
        sendMessage.sendCmd({
            type: 'users',
            target: ['user101', 'user102'],
            action: 'this action is from admin',
            from: 'admin',
            ext: {a: 'a', b: 'b'}
        });
        break;
    case 37://获取所有群组
        group.getGroups();
        break;
    case 38://获取一个或多个群组的详情
        group.getGroupDetail(['1445866257312']);
        break;
    case 39://创建一个群组
        group.createGroup({
            groupname: 'group999',
            desc: 'this is a new group created from user101',
            public: true,
            maxusers: 200,
            approval: true,
            owner: 'user101',
            members: ['user102','user103']
        });
        break;
    case 40://修改群组信息
        group.modifyGroupInfo({
            groupname: 'man',
            description: 'update groupinfo',
            maxusers: 500
        });
        break;
    case 41://删除群组
        group.deleteGroup('142833155608376');
        break;
    case 42://获取群组中成员
        group.getGroupUsers('1428032285973');
        break;
    case 43://群组加人
        group.addGroupMember('1444727980278', 'user101');
        break;
    case 44://群组批量加人
        group.addGroupMembers('1428032285973', ['user102', 'user103']);
        break;
    case 45://群组减人
        group.deleteGroupMember('1428032285973', 'user102');
        break;
    case 46://群组批量减人
        group.deleteGroupMembers('1428032285973', ['user101', 'user103']);
        break;
    case 47://获取一个用户参与的所有群组
        group.getGroupsForUser('user101');
        break;
    case 48://群组转让
        group.changeGroupOwner({
            group_id: '257315815089504684',
            newowner: 'user102'
        });
        break;
    case 49://查询一个群组黑名单中的用户名列表
        group.getGroupBlackList('1444727980278');
        break;
    case 50://群组黑名单加人
        group.addGroupBlackMember('1444727980278', 'user101');
        break;
    case 51://群组黑名单减人
        group.deleteGroupBlackMember('1444727980278', 'user101');
        break;
    case 52://上传文件
        files.uploadFile({
            filePath:'./resources/vedio/web.mp4',
            restrictAccess : 'true'
        });
        break;
    case 53://下载文件
        files.downloadFile({
            uuid: '61611e30-9b5c-11e6-b3d9-9d52b6f6416b',
            shareSecret: 'YWEeOptcEeaG8D-LXrAmEykZ07q6Q_d5jLK49nlbDAc7s3Yc'
        });
        break;
    case 54://下载图片缩略图
        files.downloadThumbnail({
            uuid: '61611e30-9b5c-11e6-b3d9-9d52b6f6416b',
            shareSecret: 'YWEeOptcEeaG8D-LXrAmEykZ07q6Q_d5jLK49nlbDAc7s3Yc',
            thumbnail: true
        });
        break;
    case 55://创建聊天室
        chatRoom.createChatRoom({
            name: 'test7',
            description: 'this chatromm is created by fengpei',
            maxusers: 200,
            owner: 'xiaoshe',
            members: ['xiaoli']
        });
        break;
    case 56://修改聊天室信息
        chatRoom.modifyChatRoom({
            chatroom_id: '1434597895697836',
            name: 'fengpeichatroom',
            description: 'this chatromm is created by fengpei in 2015',
            maxusers: 500
        });
        break;
    case 57://删除聊天室
        chatRoom.deleteChatRoom('1434597895697836');
        break;
    case 58://获取app中的所有聊天室
        chatRoom.getChatRooms();
        break;
    case 59://查看聊天室详情
        chatRoom.getChatRoomDetail('119258558918296112');
        break;
    case 60://获取用户加入的所有聊天室
        chatRoom.getChatRoomJoined('user100');
        break;
    case 61://聊天室加人
        chatRoom.addChatRoomMember('116689628932604428', 'user100');
        break;
    case 62://批量向聊天室加人
        chatRoom.addChatRoomMembers('116689628932604428', ['user100', 'user101']);
        break;
    case 63://聊天室减人
        chatRoom.deleteChatRoomMember('116707029891940804', 'user100');
        break;
    case 64://批量从聊天室减人
        chatRoom.deleteChatRoomMembers('116707029891940804', 'user100,user101');
        break;
    case 65://多次操作
        user.getUser('user100', function () {
            user.getUsers(5);
        });
        break;
}


