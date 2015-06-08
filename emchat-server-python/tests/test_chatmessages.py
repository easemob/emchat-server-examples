#!/usr/bin/python
# -*- coding: utf-8 -*-
__author__ = 'Lynch Lee'

import unittest
import json

import easemob.emchat.client.emchat_client as rest_client
from emchat.utils.types import *
from emchat.utils.loggers import Logger


class TestEaseMobChatMessagesServices(unittest.TestCase):
    logger = Logger.get_logger('TestEaseMobChatMessagesServices.class')
    users_service = rest_client.get_instance(service_users)

    def test_a_query_joined_messages(self):
        """查询默认消息记录
        """

        self.logger.info('------------api:query messages--------------------------------')

        resp = []
        try:
            resp = self.users_service.query_messages_default()
            self.assertTrue(resp[0])
            self.logger.info('query_messages_default ok, response:' + json.dumps(resp[1]))
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:query_messages_default, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:query_messages_default, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))
