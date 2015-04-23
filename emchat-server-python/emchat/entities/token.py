#!/usr/bin/python
# -*- coding: utf-8 -*-
__author__ = 'Lynch Lee'


import time
from requests.auth import AuthBase
from emchat.utils.loggers import Logger
from emchat.utils.http_utils import http_post
from emchat.utils.confs import api_host


class Token(object):
    def __init__(self, access_token, expires_in):
        self.access_token = access_token
        self.expires_at = int(time.time()) + expires_in

    def is_expired(self):
        if self.expires_at < time.time():
            return False
        else:
            return True


class BaseTokenAuth(AuthBase):

    def __call__(self, req):
        req.headers['Authorization'] = 'Bearer ' + self.get_token()
        return req

    def get_token(self):
        if (self.token is None) or (self.token.is_expired()):
            self.token = self.acquire_token()
        if self.token == None:
            return ''
        else:
            return self.token.access_token

    def acquire_token(self):
        pass


class AppClientTokenAuth(BaseTokenAuth):
    logger = Logger.get_logger('AppClientTokenAuth.class')

    def __init__(self, org, app, client_id, client_secret):
        super(AppClientTokenAuth, self).__init__()
        self.client_id = client_id
        self.client_secret = client_secret
        self.url = api_host + ("/%s/%s/token" % (org, app))
        self.token = None

    def acquire_token(self):
        payload = {
            'grant_type': 'client_credentials',
            'client_id': self.client_id,
            'client_secret': self.client_secret
        }

        resp = http_post(url=self.url, payload=payload, auth=None)
        if resp[0]:
            self.logger.info('fetched a token :' + resp[1]['access_token'])
            return Token(resp[1]['access_token'], resp[1]['expires_in'])
        else:
            self.logger.error('some errors occurred at acquiring token.')
            return None