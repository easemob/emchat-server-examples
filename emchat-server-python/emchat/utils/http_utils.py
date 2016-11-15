#!/usr/bin/python
# -*- coding: utf-8 -*-
__author__ = 'Lynch Lee'

import requests
import json

from confs import *
from emchat.utils.loggers import Logger


DEBUG = False
logger = Logger.get_logger('http_utils')


def http_post(url, payload, auth=None):
    r = requests.post(api_host + url, data=json.dumps(payload), headers=None, auth=auth)
    return http_result(r)


def get_file_stream(url):
    return requests.get(api_host + url, stream=True)


def file_upload(url, files, auth=None):
    r = requests.post(api_host + url, files=files, auth=auth)
    return http_result(r)


def http_post_form(url, payload, auth=None):
    json_header = {'content-type': 'multipart/form-data'}
    r = requests.post(api_host + url, data=json.dumps(payload), headers=json_header, auth=auth)
    return http_result(r)


def http_get(url, auth=None):
    r = requests.get(api_host + url, headers=None, auth=auth)
    return http_result(r)


def http_put(url, payload, auth=None):
    json_header = {
        'Content-type': 'application/json'
    }

    r = requests.put(api_host + url, payload, headers=json_header, auth=auth)
    return http_result(r)


def http_delete(url, auth=None):
    r = requests.delete(url, headers=None, auth=auth)
    return http_result(r)


def http_result(r):
    if DEBUG:
        logger.info('debugging ...')
        error_log = {
            'method': r.request.method,
            'url': r.request.url,
            'request_header': dict(r.request.headers),
            'response_header': dict(r.headers),
            'response': r.text
        }
        if r.request.body:
            error_log['payload'] = r.request.body
        print json.dumps(error_log)

    if r.status_code == requests.codes.ok:
        return True, r.json()
    else:
        return False, r.json()
