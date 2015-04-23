#!/usr/bin/python
# -*- coding: utf-8 -*-
__author__ = 'Lynch Lee'


from emchat.utils.http_utils import http_get, http_post, http_delete, http_put
from emchat.utils.loggers import Logger


class EMIMUsersService(object):

    logger = Logger.get_logger('EMChatIMUsersService.class')

    def __init__(self, org, app, auth=None):
        self.org = org
        self.app = app
        self.auth = auth

    def create_new_user_single(self, payload):
        """注册用户[单个]
        """

        url = ('/%s/%s/users' % (self.org, self.app))
        return http_post(url, payload, self.auth)

    def create_new_user_batch(self, payload):
        """注册IM用户[批量]
        """

        url = ('/%s/%s/users' % (self.org, self.app))
        return http_post(url, payload, self.auth)

    def im_user_login(self, payload):
        """IM用户登录
        """

        url = ('/%s/%s/users' % (self.org, self.app))
        return http_post(url, payload)

    def query_users_by_username(self, username):
        """获取IM用户[单个]
        """

        url = ('/%s/%s/users/%s' % (self.org, self.app, username))
        return http_get(url, self.auth)

    def query_users_batch(self, username):
        """获取IM用户[批量]
        """

        url = ('/%s/%s/users/%s' % (self.org, self.app, username))
        return http_get(url, self.auth)

    def delete_user_by_username(self, username):
        """删除IM用户[单个]
        """

        url = ('/%s/%s/users/%s' % (self.org, self.app, username))
        return http_delete(url, self.auth)

    def delete_user_batch(self, num):
        """删除IM用户[批量]
        """

        if isinstance(num, int):
            url = ('/%s/%s/users?limit=%s' % (self.org, self.app, num))
            return http_delete(url, self.auth)
        else:
            print('wrong type of num, it should be int')

    def modify_user_password(self, username, payload):
        """重置IM用户密码
        """

        url = ('/%s/%s/users/%s' % (self.org, self.app, username))
        return http_put(url, payload, self.auth)

    def modify_nickname_by_username(self, username, payload):
        """修改用户昵称
        """

        url = 'http://a1.easemob.com' + ('/%s/%s/users/%s' % (self.org, self.app, username))
        return http_put(url, payload, self.auth)

    def add_friend_single(self, username, friend):
        """建立好友关系
        """

        url = ('/%s/%s/users/%s/contacts/users/%s' % (self.org, self.app, username, friend))
        return http_post(url, self.auth)

    def query_friends_by_primary_key(self, username):
        """查询好友列表
        """

        url = ('/%s/%s/users/%s/contacts/users' % (self.org, self.app, username))
        return http_get(url, self.auth)

    def delete_friend_single(self, username, friend):
        """解除好友关系
        """

        url = ('/%s/%s/users/%s/contacts/users/%s' % (self.org, self.app, username, friend))
        return http_delete(url, self.auth)

    def query_offline_msg_count_of_user(self, username):
        """查询离线消息数
        """

        url = ('/%s/%s/users/%s/offline_msg_count' % (self.org, self.app, username))
        return http_get(url, self.auth)

    def query_offline_msg_status_of_user(self, username, msg_id):
        """查询某条离线消息状态
        """

        url = ('/%s/%s/users/%s/offline_msg_status/%s' % (self.org, self.app, username, msg_id))
        return http_get(url, self.auth)

    def check_status_of_user(self, username):
        """查看用户在线状态
        """

        url = ('/%s/%s/users/%s/status' % (self.org, self.app, username))
        return http_get(url, self.auth)

    def deactivate_user(self, username):
        """用户账号禁用
        """

        url = ('/%s/%s/users/%s/deactivate' % (self.org, self.app, username))
        return http_post(url, None, self.auth)

    def activate_user(self, username):
        """用户账号解禁
        """

        url = ('/%s/%s/users/%s/activate' % (self.org, self.app, username))
        return http_post(url, None, self.auth)


class EMChatGroupsService(object):

    logger = Logger.get_logger('EMChatGroupsService.class')

    def __init__(self, org, app, auth=None):
        self.org = org
        self.app = app
        self.auth = auth

    def query_all_groups(self):
        """获取所有的群组
        """

        url = ('/%s/%s/chatgroups' % (self.org, self.app))
        return http_get(url, self.auth)

    def query_all_groups_page(self, limit, cursor):
        """
        """

        ql = ''
        if isinstance(limit, int) and (limit > 0):
            ql = ql + '?limit=' + str(limit)
        if isinstance(cursor, str) and (cursor != ''):
            ql = ql + '&cursor=' + str(cursor)

        url = ('/%s/%s/chatgroups%s' % (self.org, self.app, ql))
        return http_get(url, self.auth)

    def query_groupinfo_by_groupid(self, group_id):
        """获取群组详情
        """

        url = ('/%s/%s/chatgroups/%s' % (self.org, self.app, group_id))
        return http_get(url, self.auth)

    def create_group(self, payload):
        """创建群组
        """

        url = ('/%s/%s/chatgroups' % (self.org, self.app))
        return http_post(url, payload, self.auth)

    def modify_groupinfo(self, payload):
        """修改群组信息
        """

        url = ('/%s/%s/chatgroups' % (self.org, self.app))
        return http_post(url, payload, self.auth)

    def delete_groups_by_id(self, group_id):
        """删除群组
        """

        url = ('/%s/%s/chatgroups/%s' % (self.org, self.app, group_id))
        return http_delete(url, self.auth)

    def query_all_members_by_groupid(self, group_id):
        """获取群组成员
        """

        url = ('/%s/%s/chatgroups/%s/users' % (self.org, self.app, group_id))
        resp = http_get(url, self.auth)
        if resp[0]:
            return resp[0], resp[1]['data']
        else:
            return resp[0], resp[1]

    def add_user_to_group_single(self, group_id, username):
        """群组加人[单个]
        """

        url = ('/%s/%s/chatgroups/%s/users/%s' % (self.org, self.app, group_id, username))
        return http_post(url, self.auth)

    def add_user_to_group_batch(self, group_id):
        """群组加人[批量]
        """

        paylod = {}

        url = ('/%s/%s/chatgroups/%s/users' % (self.org, self.app, group_id))
        return http_post(url, paylod, self.auth)

    def remove_user_from_group(self, group_id, username):
        """群组减人
        """

        url = ('/%s/%s/chatgroups/%s/users/%s' % (self.org, self.app, group_id, username))
        return http_delete(url)

    def query_joined_groups_for_user(self, username):
        """用户参与的所有群组
        """

        url = ('/%s/%s/users/%s/joined_chatgroups' % (self.org, self.app, username))
        return http_get(url, self.auth)


class EMChatFilesService(object):

    logger = Logger.get_logger('EMChatFilesService.class')

    def __init__(self, org, app, auth=None):
        self.org = org
        self.app = app
        self.auth = auth


class EMMessagesService(object):

    logger = Logger.get_logger('EMChatMessagesService.class')

    def __init__(self, org, app, auth=None):
        self.org = org
        self.app = app
        self.auth = auth

    def send_messages(self, payload):
        url = ('/%s/%s/messages' % (self.org, self.app))
        return http_post(url, payload, self.auth)


class EMChatMessagesService(object):

    logger = Logger.get_logger('EMChatChatMessagesService.class')

    def __init__(self, org, app, auth=None):
        self.org = org
        self.app = app
        self.auth = auth

    def query_messages_by_ql(self, limit, cursor):
        ql = ''

        if isinstance(limit, int) and (limit > 0):
            ql = ql + '?limit=' + str(limit)
        if isinstance(cursor, str) and (cursor != ''):
            ql = ql + '&cursor=' + str(cursor)

        url = ('/%s/%s/chatmessages%s' % (self.org, self.app, ql))
        return http_get(url, self.auth)

    def query_messages_default(self):
        url = ('/%s/%s/chatmessages' % (self.org, self.app))
        return http_get(url, self.auth)
