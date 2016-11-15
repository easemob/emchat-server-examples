#!/usr/bin/python
# -*- coding: utf-8 -*-
__author__ = 'Lynch Lee'


from emchat.utils.http_utils import http_get, http_post, file_upload, get_file_stream, http_put
from emchat.utils.file_utils import write
from emchat.utils.loggers import Logger


class EMIMUsersService(object):

    logger = Logger.get_logger('EMChatIMUsersService.class')

    def __init__(self, org, app, auth=None):
        self.org = org
        self.app = app
        self.auth = auth

    def create_new_user(self, payload):
        """注册IM用户
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

    def query_users(self, username):
        """获取IM用户[批量]
        """

        url = ('/%s/%s/users/%s' % (self.org, self.app, username))
        return http_get(url, self.auth)

    def modify_user_password(self, username, payload):
        """重置IM用户密码
        """

        url = ('/%s/%s/users/%s' % (self.org, self.app, username))
        return http_put(url, payload, self.auth)

    def modify_nickname(self, username, payload):
        """修改用户昵称
        """

        url = ('/%s/%s/users/%s' % (self.org, self.app, username))
        return http_put(url, payload, self.auth)

    def query_offline_msg_count_of_user(self, username):
        """查询离线消息数
        """

        url = ('/%s/%s/users/%s/offline_msg_count' % (self.org, self.app, username))
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


class EMChatFilesService(object):

    logger = Logger.get_logger('EMChatFilesService.class')

    def __init__(self, org, app, auth=None):
        self.org = org
        self.app = app
        self.auth = auth

    def upload_file(self, file_path):
        """
        上传文件
        """

        url = ("/%s/%s/chatfiles" % (self.org, self.app))
        files = {'file': ('report.xls', open(file_path, 'rb'), 'image/jpeg', {'Expires': '0'})}
        return file_upload(url, files, self.auth)

    def download_file(self, file_uuid, local_file_path):
        """
        文件下载
        """

        url = ("/%s/%s/chatfiles/%s" % (self.org, self.app, file_uuid))
        content = get_file_stream(url)

        write(local_file_path, content)


class EMMessagesService(object):

    logger = Logger.get_logger('EMChatMessagesService.class')

    def __init__(self, org, app, auth=None):
        self.org = org
        self.app = app
        self.auth = auth

    def send_messages(self, payload):
        url = ('/%s/%s/messages' % (self.org, self.app))
        return http_post(url, payload, self.auth)
