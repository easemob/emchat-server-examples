#!/usr/bin/python
# -*- coding: utf-8 -*-
__author__ = 'Lynch Lee'

import unittest
import json

import emchat.client.emchat_client as rest_client

from emchat.utils.types import *
from emchat.utils.loggers import Logger


class TestEaseMobChatFilesServices(unittest.TestCase):
    logger = Logger.get_logger('TestEaseMobChatFilesServices.class')
    service_files = rest_client.get_instance(service_chatfiles)

    def test_a_upload_file(self):
        """文件上传
        """

        self.logger.info('------------api:uploading files... --------------------------------')

        resp = []
        try:
            file_path = 'zjg.jpg'
            resp = self.service_files.upload_file(file_path)
            self.assertTrue(resp[0])
            self.logger.info('file uploading ok, response:' + json.dumps(resp[1]))
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:upload_file over, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:upload_file, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))

    def test_b_download_file(self):
        """文件下载
        """

        self.logger.info('------------api:downloading files... --------------------------------')

        resp = []
        try:
            file_path = 'zjg.jpg'
            local_file_path = '/tmp/zjg-1.jpg'
            resp = self.service_files.upload_file(file_path)
            self.assertTrue(resp[0])

            if resp[1] is not None:
                if 'entities' in resp[1]:
                    entity = resp[1]['entities'][0]
                    file_uuid = entity['uuid']

                    self.service_files.download_file(file_uuid, local_file_path)
                    self.logger.info('file download over, local file:' + local_file_path)
                    self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:downloading file over, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:downloading file over, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))
