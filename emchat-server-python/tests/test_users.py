#!/usr/bin/python
# -*- coding: utf-8 -*-
__author__ = 'Lynch Lee'

import unittest
import json

from emchat.client import emchat_client as rest_client
from emchat.utils.types import *
from emchat.utils.loggers import Logger


class TestEaseMobUserGridUsersServices(unittest.TestCase):
    logger = Logger.get_logger('TestEaseMobUserGridUsersServices.class')

    users_services = rest_client.get_instance(service_users)

    def setUp(self):
        pass
        self.users_services.delete_user_batch(100)

    def tearDown(self):
        pass
        self.users_services.delete_user_batch(100)

    def test_a_create_new_users_single(self):
        """创建单个用户
        """
        self.logger.info('-------API:create new user--------------------------------------')

        resp = []
        try:
            payload = {
                'username': 'jianxin001', 'password': '123456'
            }
            resp = self.users_services.create_new_users(payload)
            self.assertTrue(resp[0])
            self.logger.info('create_new_user_single ok, response:' + json.dumps(resp[1]))
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:create_new_users_single, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description'] + '\n'))
            raise Exception('api:create_new_users_single, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))

    def test_b_create_new_users_batch(self):
        """批量创建用户
        """
        self.logger.info('-------API:create new users batch------------------------------')
        resp = []
        try:
            payload = [
                {'username': 'jianxin002', 'password': '123456'},
                {'username': 'jianxin003', 'password': '123456'},
                {'username': 'jianxin004', 'password': '123456'},
                {'username': 'jianxin005', 'password': '123456'},
                {'username': 'jianxin006', 'password': '123456'},
                {'username': 'jianxin007', 'password': '123456'},
                {'username': 'jianxin008', 'password': '123456'},
                {'username': 'jianxin009', 'password': '123456'},
                {'username': 'jianxin010', 'password': '123456'},
                {'username': 'jianxin011', 'password': '123456'},
                {'username': 'jianxin012', 'password': '123456'}
            ]
            resp = self.users_services.create_new_users(payload)
            self.assertTrue(resp[0])
            self.logger.info('create_new_users_batch ok, response:' + json.dumps(resp[1]))
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:create_new_users_batch, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:create_new_users_batch, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))

    def test_c_im_user_login(self):
        """IM用户登录
        """
        self.logger.info('-------API:im user login by username/password------------------')

        resp = []
        try:
            payload = {
                'username': 'jianxin013', 'password': '123456'
            }
            payload1 = {
                'grant_type': 'password', 'username': 'jianxin013', 'password': '123456'
            }
            resp = self.users_services.create_new_users(payload)
            self.assertTrue(resp[0])
            resp = self.users_services.im_user_login(payload1)
            self.assertTrue(resp[0])
            self.logger.info('try login 1st, token:' + str(resp[1]['access_token']))
            resp = self.users_services.im_user_login(payload1)
            self.assertTrue(resp[0])
            self.logger.info('try login 2nd, token:' + str(resp[1]['access_token']))
            self.logger.info('im_user_login ok, response:' + json.dumps(resp[1]))
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:im_user_login, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:im_user_login, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))

    def test_d_query_users_by_username(self):
        """通过用户名查找用户
        """
        self.logger.info('-------API:query user by username----------------------------')

        resp = []
        try:
            payload = {
                'username': 'jianxin015', 'password': '123456'
            }
            resp = self.users_services.create_new_users(payload)
            self.assertTrue(resp[0])
            self.logger.info('created user:' + payload['username'])
            resp = self.users_services.query_users_by_primary_key(payload['username'])
            self.assertTrue(resp[0])
            self.logger.info('query_users_by_username:' + payload['username'] +
                             ' successfully, response:' + json.dumps(resp[1]))
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:query_users_by_username, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:query_users_by_username, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))

    def test_e_query_users_ql(self):
        """批量查找用户
        """
        self.logger.info('-------API:query users, paging display------------------------------')

        resp = []
        try:
            payload = [
                {'username': 'jianxin016', 'password': '123456'},
                {'username': 'jianxin017', 'password': '123456'},
                {'username': 'jianxin018', 'password': '123456'},
                {'username': 'jianxin019', 'password': '123456'},
                {'username': 'jianxin020', 'password': '123456'},
                {'username': 'jianxin021', 'password': '123456'},
                {'username': 'jianxin022', 'password': '123456'},
                {'username': 'jianxin023', 'password': '123456'},
                {'username': 'jianxin024', 'password': '123456'},
                {'username': 'jianxin025', 'password': '123456'},
                {'username': 'jianxin026', 'password': '123456'}
            ]
            resp = self.users_services.create_new_users(payload)
            self.assertTrue(resp[0])
            self.logger.info('create 11 users')
            resp = self.users_services.query_users_ql(10, '')
            self.assertTrue(resp[0])
            self.logger.info('query users by ql[limit=10], page1:' + json.dumps(resp[1]))
            cursor = resp[1]['cursor']
            resp = self.users_services.query_users_ql(10, str(cursor))
            self.assertTrue(resp[0])
            self.logger.info('query users by ql[limit=10,cursor=' + cursor + '] , page2:' + json.dumps(resp[1]))
            self.logger.info('query_users_ql ok')
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:query_users_ql, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:query_users_ql, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))
        except KeyError:
            self.logger.error('api:query_users_ql, result:failure, ' +
                              'cause by: not found json-key from response') + '\n'
            raise Exception('api:query_users_ql, result:failure! ' +
                            'cause by: not found json-key from response')

    def test_f_modify_user_password_with_admin_token(self):
        """修改用户密码[带管理员token]
        """
        self.logger.info('-------API:modify user password with admin token----------')

        resp = []
        try:
            username = 'jianxin027'
            payload = {
                'username': 'jianxin027', 'password': '123456'
            }
            payload1 = {
                'newpassword': '1234'
            }
            payload2 = {
                'grant_type': 'password', 'username': 'jianxin027', 'password': '1234'
            }
            resp = self.users_services.create_new_users(payload)
            self.assertTrue(resp[0])
            self.logger.info('created user:jianxin027')
            resp = self.users_services.modify_user_password_with_admin_token(username, json.dumps(payload1))
            self.assertTrue(resp[0])
            self.logger.info('reset password:1234 for user:jianxin027')
            self.logger.info('try login with new password')
            resp = self.users_services.im_user_login(payload2)
            self.assertTrue(resp[0])
            self.logger.info('modify_user_password_with_admin_token ok, response:' + json.dumps(resp[1]))
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:modify_user_password_with_admin_token, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:modify_user_password_with_admin_token, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))

    def test_g_add_friend_single(self):
        """添加用户好友[单个]
        """
        self.logger.info('-------API:add friend-----------------------------------')

        resp = []
        try:
            payload = {}
            usernamepayload = {
                'username': 'jianxin028', 'password': '123456'
            }
            friendpayload = {
                'username': 'jianxin029', 'password': '123456'
            }
            resp = self.users_services.create_new_users(usernamepayload)
            self.assertTrue(resp[0])
            resp = self.users_services.create_new_users(friendpayload)
            self.assertTrue(resp[0])
            self.logger.info('add user: ' + friendpayload['username'] + ' as the friend of ' +
                             usernamepayload['username'])
            resp = self.users_services.add_friend_single(usernamepayload['username'],
                                                         friendpayload['username'],
                                                         payload)
            self.assertTrue(resp[0])
            self.logger.info('add_friend_single ok, response:' + json.dumps(resp[1]))
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:add_friend_single, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:add_friend_single, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))

    def test_h_query_friends_by_username(self):
        """查找用户好友
        """
        self.logger.info('------API:query all friends of user-------------------')

        resp = []
        try:
            username = 'jianxin030'
            friend = 'jianxin031'
            payload = {}
            payload1 = {
                'username': 'jianxin030', 'password': '123456'
            }
            payload2 = {
                'username': 'jianxin031', 'password': '123456'
            }
            resp = self.users_services.create_new_users(payload1)
            self.assertTrue(resp[0])
            self.logger.info('created user:' + username)
            resp = self.users_services.create_new_users(payload2)
            self.assertTrue(resp[0])
            self.logger.info('created user:' + friend)
            resp = self.users_services.add_friend_single(username, friend, payload)
            self.assertTrue(resp[0])
            self.logger.info('add user:' + friend + ' as the friend of user:' + username)
            resp = self.users_services.query_friends_by_primary_key(username)
            self.assertTrue(resp[0])
            self.logger.info('query all friends of user:' + username + 'response:' + json.dumps(resp[1]))
            self.logger.info('query all friends of user ok')
            resp = self.users_services.delete_friend_single(username, friend)
            self.assertTrue(resp[0])
            self.logger.info('unfriended')
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:add_friend_single, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:add_friend_single, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))

    def test_i_delete_friend_single(self):
        """接触好友关系
        """
        self.logger.info('-------API:delete friend------------------------------')

        resp = []
        try:
            username = 'jianxin032'
            friend = 'jianxin033'
            payload = {}
            payload1 = {
                'username': 'jianxin032', 'password': '123456'
            }
            payload2 = {
                'username': 'jianxin033', 'password': '123456'
            }
            resp = self.users_services.create_new_users(payload1)
            self.assertTrue(resp[0])
            self.logger.info('created user:' + username)
            resp = self.users_services.create_new_users(payload2)
            self.assertTrue(resp[0])
            self.logger.info('created user:' + friend)
            resp = self.users_services.add_friend_single(username, friend, payload)
            self.assertTrue(resp[0])
            self.logger.info('add user:' + friend + ' as the friend of user:' + username)
            resp = self.users_services.delete_friend_single(username, friend)
            self.assertTrue(resp[0])
            self.logger.info('delete_friend_single ok, response:' + json.dumps(resp[1]))
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:delete_friend_single, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:delete_friend_single, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))

    def test_j_add_user_into_blacklist_by_username(self):
        """用户黑名单加人
        """
        self.logger.info('-------API:add user into blacklist--------------------')

        resp = []
        try:
            username = 'jianxin034'
            stranger = 'jianxin035'
            payload = {}
            payload1 = {
                'username': 'jianxin034', 'password': '123456'
            }
            payload2 = {
                'username': 'jianxin035', 'password': '123456'
            }
            resp = self.users_services.create_new_users(payload1)
            self.assertTrue(resp[0])
            self.logger.info('created user:' + username)
            resp = self.users_services.create_new_users(payload2)
            self.assertTrue(resp[0])
            self.logger.info('created user:' + stranger)
            self.logger.info('add user:' + username + ' into user:' + stranger + '\'s blacklist')
            resp = self.users_services.add_user_into_blacklist_by_username(username, payload, stranger)
            self.assertTrue(resp[0])
            self.logger.info('add_user_into_blacklist_by_username ok, response:' + json.dumps(resp[1]))
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:add_user_into_blacklist_by_username, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:add_user_into_blacklist_by_username, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))

    def test_k_query_blacklist_by_username(self):
        """查看用户黑名单
        """
        self.logger.info('-------API:query blacklist--------------------------')

        resp = []
        try:
            username = 'jianxin036'
            stranger = 'jianxin037'
            payload = {}
            payload1 = {
                'username': 'jianxin036', 'password': '123456'
            }
            payload2 = {
                'username': 'jianxin037', 'password': '123456'
            }
            resp = self.users_services.create_new_users(payload1)
            self.assertTrue(resp[0])
            self.logger.info('created user:' + username)
            resp = self.users_services.create_new_users(payload2)
            self.assertTrue(resp[0])
            self.logger.info('created user:' + stranger)
            self.logger.info('add user:' + username + ' into user:' + stranger + '\'s blacklist')
            resp = self.users_services.add_user_into_blacklist_by_username(username, payload, stranger)
            self.assertTrue(resp[0])
            resp = self.users_services.query_blacklist_by_username(username)
            self.assertTrue(resp[0])
            self.logger.info('query_blacklist_by_username ok, response:' + json.dumps(resp[1]))
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:query_blacklist_by_username, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:query_blacklist_by_username, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))

    def test_l_remove_user_from_blacklist_by_username(self):
        """用户黑名单减人
        """
        self.logger.info('-------API:remove user from blacklist---------------')

        resp = []
        try:
            username = 'jianxin038'
            stranger = 'jianxin039'
            payload = {}
            payload1 = {
                'username': 'jianxin038', 'password': '123456'
            }
            payload2 = {
                'username': 'jianxin039', 'password': '123456'
            }
            resp = self.users_services.create_new_users(payload1)
            self.assertTrue(resp[0])
            self.logger.info('created user:' + username)
            resp = self.users_services.create_new_users(payload2)
            self.assertTrue(resp[0])
            self.logger.info('created user:' + stranger)
            self.logger.info('add user:' + username + ' into user:' + stranger + '\'s blacklist')
            resp = self.users_services.add_user_into_blacklist_by_username(username, payload, stranger)
            self.assertTrue(resp[0])
            resp = self.users_services.remove_user_from_blacklist_by_username(username, stranger)
            self.assertTrue(resp[0])
            self.logger.info('remove_user_from_blacklist_by_username ok, response:' + json.dumps(resp[1]))
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:remove_user_from_blacklist_by_username, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:remove_user_from_blacklist_by_username, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))

    def test_m_query_user_offline_msg_status(self):
        """查看用户的离线消息状态
        """
        self.logger.info('-------API:check the status of offline message for user-')

        resp = []
        try:
            username = 'jianxin040'
            msg_id = 'adsfadfadf'
            payload1 = {
                'username': 'jianxin040', 'password': '123456'
            }
            resp = self.users_services.create_new_users(payload1)
            self.assertTrue(resp[0])
            self.logger.info('created user:' + username)
            resp = self.users_services.query_user_offline_msg_status(username, msg_id)
            self.assertTrue(resp[0])
            self.logger.info('query_user_offline_msg_status ok, response:' + json.dumps(resp[1]))
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:remove_user_from_blacklist_by_username, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:remove_user_from_blacklist_by_username, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))

    def test_m_delete_user_by_primary_key(self):
        """删除用户
        """
        self.logger.info('-------API:delete user by username---------------------')
        resp = []
        try:
            username = 'jianxin041'
            payload1 = {
                'username': 'jianxin041', 'password': '123456'
            }
            resp = self.users_services.create_new_users(payload1)
            self.assertTrue(resp[0])
            self.logger.info('created user:' + username)
            resp = self.users_services.delete_user_by_primary_key(username)
            self.assertTrue(resp[0])
            self.logger.info('delete_user_by_username ok, response:' + json.dumps(resp[1]))
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:delete_user_by_username, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:delete_user_by_username, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))

    def test_n_delete_user_batch(self):
        """批量删除用户[100个]
        """
        self.logger.info('-------API:delete users batch--------------------------')
        resp = []
        try:
            payload1 = {
                'username': 'jianxin042', 'password': '123456'
            }
            payload2 = {
                'username': 'jianxin043', 'password': '123456'
            }
            resp = self.users_services.create_new_users(payload1)
            self.assertTrue(resp[0])
            self.logger.info('created user:' + payload1['username'])
            resp = self.users_services.create_new_users(payload2)
            self.assertTrue(resp[0])
            self.logger.info('created user:' + payload2['username'])
            resp = self.users_services.delete_user_batch(100)
            self.assertTrue(resp[0])
            self.logger.info('delete_user_batch ok, response:' + json.dumps(resp[1]))
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:delete_user_batch, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:delete_user_batch, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))

    def test_a_check_status_of_user(self):
        """查询用户在线状态
        """
        self.logger.info('-----------API:check the status of user-----------------------------------')
        resp = []
        try:
            payload1 = {
                'username': 'jianxinm0011', 'password': '123456'
            }
            resp = self.users_services.create_new_users(payload1)
            self.assertTrue(resp[0])
            self.logger.info('created user:' + payload1['username'])
            resp = self.users_services.check_status_of_user(payload1['username'])
            self.assertTrue(resp[0])
            self.logger.info('check_status_of_user ok, response:' + json.dumps(resp[1]))
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:check_status_of_user, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:check_status_of_user, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))