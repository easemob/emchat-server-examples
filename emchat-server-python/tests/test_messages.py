#!/usr/bin/python
# -*- coding: utf-8 -*-
__author__ = 'Lynch Lee'

import unittest
import json

import emchat.client.emchat_client as rest_client
from emchat.utils.types import service_users, service_messages
from emchat.utils.loggers import Logger


class TestEaseMobMessagesServices(unittest.TestCase):

    logger = Logger.get_logger('TestEaseMobMessagesServices.class')

    message_services = rest_client.get_instance(service_users)
    users_services = rest_client.get_instance(service_messages)

    def setUp(self):
        self.users_services.delete_user_batch(100)

    def tearDown(self):
        self.users_services.delete_user_batch(100)

    def test_b_send_messages_to_user(self):
        """给用户发送文字消息
        """

        self.logger.info('----------API:send text messages to user---------------------------------')
        resp = []
        try:
            payload = {
                'target_type': 'users',
                'target': ['jianxinm0023'],
                'msg': {'type': 'txt', 'msg': 'hello from rest'},
                'from': 'jianxinm0033',
                'ext': {'attr1': 'v1', 'attr2': 'v2'}
            }

            payload1 = {
                'username': 'jianxinm0023', 'password': '123456'
            }
            payload2 = {
                'username': 'jianxinm0033', 'password': '123456'
            }
            resp = self.users_services.create_new_users(payload1)
            self.assertTrue(resp[0])
            self.logger.info('created user:' + payload1['username'])
            resp = self.users_services.create_new_users(payload2)
            self.assertTrue(resp[0])
            self.logger.info('created user:' + payload2['username'])
            self.logger.info('send txt message from :' + payload2['username'] + ' from:' + payload1['username'])
            resp = self.message_services.send_messages(payload)
            self.assertTrue(resp[0])
            self.logger.info('send_messages ok, response:' + json.dumps(resp[1]))
            resp = self.users_services.delete_user_by_primary_key('jianxinm0023')
            self.assertTrue(resp[0])
            self.logger.info('deleted user:' + payload1['username'])
            resp = self.users_services.delete_user_by_primary_key('jianxinm0033')
            self.assertTrue(resp[0])
            self.logger.info('deleted user:' + payload2['username'])
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:send_messages, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:send_messages, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))