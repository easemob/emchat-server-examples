/**
 * Hyphenate nodeJS REST API sample code
 * Copyright(c) 2016 www.hyphenate.io
 * Author: Hyphenate <info@hyphenate.io>
 */
var Token = require('./hyphenate/token');
var User = require('./hyphenate/user');
var ChatHistory = require('./hyphenate/chatHistory');
var Files = require('./hyphenate/files');
var Group = require('./hyphenate/group');
var ChatRoom = require('./hyphenate/chatRoom');
var SendMessage = require('./hyphenate/sendMessage');

token = new Token();
user = new User();
chatHistory = new ChatHistory();
files = new Files();
group = new Group();
chatRoom = new ChatRoom();
sendMessage = new SendMessage();

console.log('test.js is running');

// pick a test
var i = 15;

switch (i) {

    case 10:    //Request an Authentication Token
        token.accessToken(function (data) {@
            console.log(data);
        });
        break;
    case 11:    //Create a user
        user.createUser('user101', '123456');
        break;
    case 12:    //Create multiple users
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
    case 13:    //Get a user
        user.getUser('user101');
        break;
    case 14:    //Get users in batch
        user.getUsers(5);
        break;
    case 15:    //Delete a user
        user.deleteUser('user101');
        break;
    case 16:    //Delete users in batch
        user.deleteUsers(2, '${cursor}');
        break;
    case 17:    //Reset user's password
        user.resetPassword('user101', '123456', '654321');
        break;
    case 18:    //Update user's nickname
        user.editNickname('user101', 'orange');
        break;
    case 19:    //Add a friend for user
        user.addFriend('user101', 'user102');
        break;
    case 20:    //Delete a friend for user
        user.deleteFriend('user101', 'user102');
        break;
    case 21:    //Get user's friends list
        user.showFriends('user101');
        break;
    case 22:    //Get user's blacklist
        user.getBlacklist('user101');
        break;
    case 23:    //Block user(s)
        user.addUserForBlacklist('user101', ['user102', 'user103']);
        break;
    case 24:    //UnBlock user(s)
        user.deleteUserFromBlacklist('user101', 'user102');
        break;
    case 25:    //Get user online status
        user.isOnline('user101');
        break;
    case 26:    //Get offline message count
        user.getOfflineMessages('user101');
        break;
    case 27:    //Get offline message status
        user.getOfflineMessageStatus('user101', '${msgid}');
        break;
    case 28:    //Deactivate user account
        user.deactivateUser('user101');
        break;
    case 29:    //Activation user account
        user.activateUser('user101');
        break;
    case 30:    //Logout user
        user.disconnectUser('user101');
        break;
    case 31:    //Get chat history
        chatHistory.getChatMessages('${ql}', '${cursor}');
        break;
    case 32:    //Send text message
        sendMessage.sendText({
            type: 'users',
            target: ['user101'],
            content: 'this text is from admin',
            from: 'admin',
            ext: {a: 'a', b: 'b', c: 'c'}
        });
        break;
    case 33:    //Send image message
        sendMessage.sendImage({
            type: 'users',
            target: ['user101', 'user102'],
            url: 'https://a1.easemob.com/dihon/loveofgod/chatfiles/61611e30-9b5c-11e6-b3d9-9d52b6f6416b',
            filename: 'dog.jpg',
            secret: 'YWEeOptcEeaG8D-LXrAmEykZ07q6Q_d5jLK49nlbDAc7s3Yc',
            from: 'admin',
            ext: {a: 'a', b: 'b'}
        });
        break;
    case 34:    //Send audio message
        sendMessage.sendAudio({
            type: 'users',
            target: ['user101', 'user102'],
            url: 'https://a1.easemob.com/dihon/loveofgod/chatfiles/83419440-9b79-11e6-a408-01f417d892b0',
            filename: 'song.mp3',
            length: 10,
            secret: 'g0GUSpt5Eea-E78LqsqYRGrpCneE0xwEXPN8uvq327wtIiJ2',
            from: 'admin',
            ext: {a: 'a', b: 'b'}
        });
        break;
    case 35:    //Send video message
        sendMessage.sendVideo({
            type: 'users',
            target: ['user101', 'user102'],
            url: 'https://a1.easemob.com/dihon/loveofgod/chatfiles/a4eaacd0-9b79-11e6-992f-b32958bd06ae',
            filename: 'web.mp4',
            thumb: 'https://a1.easemob.com/dihon/loveofgod/chatfiles/17b55630-13d5-11e5-9570-e1734d6149fa',
            length: 10,
            file_length: 42190,
            thumb_secret: 'F7VWOhPVEeWrHbXQmsnDQAdyk4NuijHO1CemodZL8WMZRY1u',
            secret: 'pOqs2pt5EeaLsPXcXfAk0UrWaF5EEV9SNsCwxhMmT-ClN0PG',
            from: 'admin',
            ext: {a: 'a', b: 'b'}
        });
        break;
    case 36:    //Send commend message
        sendMessage.sendCmd({
            type: 'users',
            target: ['user101', 'user102'],
            action: 'this action is from admin',
            from: 'admin',
            ext: {a: 'a', b: 'b'}
        });
        break;
    case 37:    //Get all groups
        group.getGroups();
        break;
    case 38:    //Get group(s) detail
        group.getGroupDetail(['1445866257312']);
        break;
    case 39:    //Create a group
        group.createGroup({
            groupname: 'fruit club',
            desc: 'this is a new group created from user101',
            public: true,
            maxusers: 200,
            approval: true,
            owner: 'user101',
            members: ['user102', 'user103']
        });
        break;
    case 40:    //Modify group information
        group.modifyGroupInfo({
            groupname: 'fruit club',
            description: 'update group info',
            maxusers: 500
        });
        break;
    case 41:    //Delete a group
        group.deleteGroup('142833155608376');
        break;
    case 42:    //Get members of Group
        group.getGroupUsers('1428032285973');
        break;
    case 43:    //Add a user to group
        group.addGroupMember('1444727980278', 'user101');
        break;
    case 44:    //Add multiple users to group
        group.addGroupMembers('1428032285973', ['user102', 'user103']);
        break;
    case 45:    //Remove a member from group
        group.deleteGroupMember('1428032285973', 'user102');
        break;
    case 46:    //Remove multiple members from group
        group.deleteGroupMembers('1428032285973', ['user101', 'user103']);
        break;
    case 47:    //Get a list of groups of user joined
        group.getGroupsForUser('user101');
        break;
    case 48:    //Update group owner
        group.changeGroupOwner({
            group_id: '257315815089504684',
            newowner: 'user102'
        });
        break;
    case 49:    //Get group blocked user
        group.getGroupBlackList('1444727980278');
        break;
    case 50:    //Add user to blacklist of group
        group.addGroupBlackMember('1444727980278', 'user101');
        break;
    case 51:    //Remove user from blacklist of group
        group.deleteGroupBlackMember('1444727980278', 'user101');
        break;
    case 52:    //Upload file
        files.uploadFile({
            filePath: './resources/video/web.mp4',
            restrictAccess: 'true'
        });
        break;
    case 53:    //Download file
        files.downloadFile({
            uuid: '61611e30-9b5c-11e6-b3d9-9d52b6f6416b',
            shareSecret: 'YWEeOptcEeaG8D-LXrAmEykZ07q6Q_d5jLK49nlbDAc7s3Yc'
        });
        break;
    case 54:    //Download thumbnail
        files.downloadThumbnail({
            uuid: '61611e30-9b5c-11e6-b3d9-9d52b6f6416b',
            shareSecret: 'YWEeOptcEeaG8D-LXrAmEykZ07q6Q_d5jLK49nlbDAc7s3Yc',
            thumbnail: true
        });
        break;
    case 55:    //Create a chat room
        chatRoom.createChatRoom({
            name: 'a new chat room',
            description: 'this chat room is created by user101',
            maxusers: 200,
            owner: 'user101',
            members: ['user102']
        });
        break;
    case 56:    //Update chat room details
        chatRoom.modifyChatRoom({
            chatroom_id: '1434597895697836',
            name: 'fruit club',
            description: 'this chat room is created by user101',
            maxusers: 500
        });
        break;
    case 57:    //Delete a chat room
        chatRoom.deleteChatRoom('1434597895697836');
        break;
    case 58:    //Get all chat rooms
        chatRoom.getChatRooms();
        break;
    case 59:    //Get chat room detail
        chatRoom.getChatRoomDetail('119258558918296112');
        break;
    case 60:    //Get all chat room of user joined
        chatRoom.getChatRoomJoined('user100');
        break;
    case 61:    //Add a member to chat room
        chatRoom.addChatRoomMember('116689628932604428', 'user100');
        break;
    case 62:    //Add multiple members to chat room
        chatRoom.addChatRoomMembers('116689628932604428', ['user100', 'user101']);
        break;
    case 63:    //Remove a member from chat room
        chatRoom.deleteChatRoomMember('116707029891940804', 'user100');
        break;
    case 64:    //Remove multiple member from chat room
        chatRoom.deleteChatRoomMembers('116707029891940804', 'user100,user101');
        break;
}

