#!/usr/bin/python
# -*- coding: utf-8 -*-
__author__ = 'Lynch Lee'

import unittest
import json

import emchat.client.emchat_client as rest_client
from emchat.utils.types import *
from emchat.utils.loggers import Logger


class TestEaseMobUserGridChatGroupsServices(unittest.TestCase):
    logger = Logger.get_logger('TestEaseMobUserGridChatGroupsServices.class')

    groups_services = rest_client.get_instance(service_chatgroups)
    users_services = rest_client.get_instance(service_users)

    def setUp(self):
        pass
        self.users_services.delete_user_batch(100)

    def tearDown(self):
        pass
        self.users_services.delete_user_batch(100)

    def test_a_create_group(self):
        """创建群组
        """
        self.logger.info('-------API:create group------------------------')
        resp = []
        try:
            # setup
            payload = {
                "groupname": "testrestgrp12",
                "desc": "server create group",
                "public": True,
                "maxusers": 300,
                "approval": True,
                "owner": "jianxincg001",
                "members": ["jianxincg002", "jianxincg003"]
            }
            payload1 = [
                {'username': 'jianxincg001', 'password': '123456'},
                {'username': 'jianxincg002', 'password': '123456'},
                {'username': 'jianxincg003', 'password': '123456'}
            ]

            resp = self.users_services.create_new_users(payload1)
            self.assertTrue(resp[0])
            self.logger.info('created user:' +
                             resp[1]['entities'][0]['username'] + ', ' +
                             resp[1]['entities'][1]['username'] + ', ' +
                             resp[1]['entities'][2]['username'] + ' as members')
            
            self.logger.info('creating chatgroup ...')
            resp = self.groups_services.create_group(payload)
            self.assertTrue(resp[0])
            group_id = resp[1]['data']['groupid']
            self.logger.info('created chatgroup:' + group_id)
            self.logger.info('create_group ok, response:' + json.dumps(resp[1]))

            resp = self.groups_services.remove_user_from_group(group_id, 'jianxincg002')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg001 from group:' + group_id)
            resp = self.groups_services.remove_user_from_group(group_id, 'jianxincg003')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg003 from group:' + group_id)
            resp = self.groups_services.delete_groups_by_id(group_id)
            self.assertTrue(resp[0])
            self.logger.info('deleted chatgroup:' + group_id)
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:create_group, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:create_group, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))
        except KeyError:
            self.logger.error('api:create_group, result:failure, ' +
                              'cause by: not found json-key from response' + '\n')
            raise Exception('api:create_group, result:failure! ' +
                            'cause by: not found json-key from response')

    def test_b_query_all_groups(self):
        """查询群组列表[不分页]
        """
        self.logger.info('-------API:query all groups-----------------------')

        resp = []
        try:
            payload = {
                "groupname": "testrestgrp12",
                "desc": "server create group",
                "public": True,
                "maxusers": 300,
                "approval": True,
                "owner": "jianxincg004",
                "members": ["jianxincg005", "jianxincg006"]
            }
            payload1 = [
                {'username': 'jianxincg004', 'password': '123456'},
                {'username': 'jianxincg005', 'password': '123456'},
                {'username': 'jianxincg006', 'password': '123456'}
            ]
            resp = self.users_services.create_new_users(payload1)
            self.assertTrue(resp[0])
            self.logger.info('created user:' +
                             resp[1]['entities'][0]['username'] +
                             ',' + resp[1]['entities'][1]['username'] +
                             ',' + resp[1]['entities'][2]['username'] + ' as members')
            resp = self.groups_services.create_group(payload)
            self.assertTrue(resp[0])
            group_id = resp[1]['data']['groupid']
            self.logger.info('created chatgroup:' + group_id)
            self.logger.info('query all chatgroups ...')
            resp = self.groups_services.query_all_groups()
            self.assertTrue(resp[0])
            group_id = resp[1]['data'][0]['groupid']
            self.logger.info('found all groups[' + group_id + ']')
            self.logger.info('query_all_groups ok, response:' + json.dumps(resp[1]))

            resp = self.groups_services.remove_user_from_group(group_id, 'jianxincg005')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg005 from group:' + group_id)
            resp = self.groups_services.remove_user_from_group(group_id, 'jianxincg006')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg006 from group:' + group_id)
            resp = self.groups_services.delete_groups_by_id(group_id)
            self.assertTrue(resp[0])
            self.logger.info('deleted group:' + group_id)
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:query_all_groups, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:query_all_groups, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))
        except KeyError:
            self.logger.error('api:query_all_groups, result:failure, ' +
                              'cause by: not found json-key from response' + '\n')
            raise Exception('api:query_all_groups, result:failure! ' +
                            'cause by: not found json-key from response')

    def test_c_query_all_groups_page(self):
        """查询群组列表[分页]
        """
        self.logger.info('-------API:query all groups, paging display----------')

        resp = []
        try:
            payload = {
                "groupname": "testrestgrp12",
                "desc": "server create group",
                "public": True,
                "maxusers": 300,
                "approval": True,
                "owner": "jianxincg007",
                "members": ["jianxincg008", "jianxincg009"]
            }
            payload1 = [
                {'username': 'jianxincg007', 'password': '123456'},
                {'username': 'jianxincg008', 'password': '123456'},
                {'username': 'jianxincg009', 'password': '123456'}
            ]
            resp = self.users_services.create_new_users(payload1)
            self.assertTrue(resp[0])
            self.logger.info('created user:' +
                             resp[1]['entities'][0]['username'] +
                             ',' + resp[1]['entities'][1]['username'] +
                             ',' + resp[1]['entities'][2]['username'] + ' as members')
            resp = self.groups_services.create_group(payload)
            self.assertTrue(resp[0])
            group_id1 = resp[1]['data']['groupid']
            self.logger.info('created chatgroup:' + group_id1)
            resp = self.groups_services.create_group(payload)
            self.assertTrue(resp[0])
            group_id2 = resp[1]['data']['groupid']
            self.logger.info('created chatgroup:' + group_id2)
            resp = self.groups_services.create_group(payload)
            self.assertTrue(resp[0])
            group_id3 = resp[1]['data']['groupid']
            self.logger.info('created chatgroup:' + group_id3)
            resp = self.groups_services.create_group(payload)
            self.assertTrue(resp[0])
            group_id4 = resp[1]['data']['groupid']
            self.logger.info('created chatgroup:' + group_id4)
            resp = self.groups_services.create_group(payload)
            self.assertTrue(resp[0])
            group_id5 = resp[1]['data']['groupid']
            self.logger.info('created chatgroup:' + group_id5)
            resp = self.groups_services.create_group(payload)
            self.assertTrue(resp[0])
            group_id6 = resp[1]['data']['groupid']
            self.logger.info('created chatgroup:' + group_id6)
            resp = self.groups_services.query_all_groups_page(10, 11)
            self.assertTrue(resp[0])
            self.logger.info('query all groups by page ..., limit[10],cursor[11], found ' +
                             str(len(resp[1]['data'])) + ' groups')
            self.logger.info('query_all_groups_page ok, response:' + json.dumps(resp[1]))

            resp = self.groups_services.remove_user_from_group(group_id1, 'jianxincg008')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg008 from group:' + group_id1)
            resp = self.groups_services.remove_user_from_group(group_id1, 'jianxincg009')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg009 from group:' + group_id1)
            resp = self.groups_services.delete_groups_by_id(group_id1)
            self.assertTrue(resp[0])
            self.logger.info('deleted group:' + group_id1)

            resp = self.groups_services.remove_user_from_group(group_id2, 'jianxincg008')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg008 from group:' + group_id2)
            resp = self.groups_services.remove_user_from_group(group_id2, 'jianxincg009')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg009 from group:' + group_id2)
            resp = self.groups_services.delete_groups_by_id(group_id2)
            self.assertTrue(resp[0])
            self.logger.info('deleted group:' + group_id2)

            resp = self.groups_services.remove_user_from_group(group_id3, 'jianxincg008')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg008 from group:' + group_id3)
            resp = self.groups_services.remove_user_from_group(group_id3, 'jianxincg009')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg009 from group:' + group_id3)
            resp = self.groups_services.delete_groups_by_id(group_id3)
            self.assertTrue(resp[0])
            self.logger.info('deleted group:' + group_id3)

            resp = self.groups_services.remove_user_from_group(group_id4, 'jianxincg008')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg008 from group:' + group_id4)
            resp = self.groups_services.remove_user_from_group(group_id4, 'jianxincg009')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg009 from group:' + group_id4)
            resp = self.groups_services.delete_groups_by_id(group_id4)
            self.assertTrue(resp[0])
            self.logger.info('deleted group:' + group_id4)

            resp = self.groups_services.remove_user_from_group(group_id5, 'jianxincg008')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg008 from group:' + group_id5)
            resp = self.groups_services.remove_user_from_group(group_id5, 'jianxincg009')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg009 from group:' + group_id5)
            resp = self.groups_services.delete_groups_by_id(group_id5)
            self.assertTrue(resp[0])
            self.logger.info('deleted group:' + group_id5)

            resp = self.groups_services.remove_user_from_group(group_id6, 'jianxincg008')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg008 from group:' + group_id6)
            resp = self.groups_services.remove_user_from_group(group_id6, 'jianxincg009')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg009 from group:' + group_id6)
            resp = self.groups_services.delete_groups_by_id(group_id6)
            self.assertTrue(resp[0])
            self.logger.info('deleted group:' + group_id6)
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:query_all_groups_page, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:query_all_groups_page, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))
        except KeyError:
            self.logger.error('api:query_all_groups_page, result:failure, ' +
                              'cause by: not found json-key from response' + '\n')
            raise Exception('api:query_all_groups_page, result:failure! ' +
                            'cause by: not found json-key from response')

    def test_d_query_groupinfo_by_groupids(self):
        """查询群组详情
        """
        self.logger.info('------API:query groupinfo by groupids---------------------')

        resp = []
        try:
            payload = {
                "groupname": "testrestgrp12",
                "desc": "server create group",
                "public": True,
                "maxusers": 300,
                "approval": True,
                "owner": "jianxincg010",
                "members": ["jianxincg011", "jianxincg012"]
            }
            payload1 = [
                {'username': 'jianxincg010', 'password': '123456'},
                {'username': 'jianxincg011', 'password': '123456'},
                {'username': 'jianxincg012', 'password': '123456'}
            ]
            resp = self.users_services.create_new_users(payload1)
            self.assertTrue(resp[0])
            self.logger.info('created user:' +
                             resp[1]['entities'][0]['username'] +
                             ',' + resp[1]['entities'][1]['username'] +
                             ',' + resp[1]['entities'][2]['username'] + ' as members')
            resp = self.groups_services.create_group(payload)
            self.assertTrue(resp[0])
            group_id = resp[1]['data']['groupid']
            self.logger.info('created chatgroup:' + group_id)
            self.logger.info('query groupinfo by groupid[' + group_id + '] ...')
            resp = self.groups_services.query_groupinfo_by_groupids(group_id)
            self.assertTrue(resp[0])
            self.logger.info('query_groupinfo_by_groupids ok, response:'
                             + json.dumps(resp[1]))

            resp = self.groups_services.remove_user_from_group(group_id, 'jianxincg011')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg011 from group:' + group_id)
            resp = self.groups_services.remove_user_from_group(group_id, 'jianxincg012')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg012 from group:' + group_id)
            resp = self.groups_services.delete_groups_by_id(group_id)
            self.assertTrue(resp[0])
            self.logger.info('deleted group:' + group_id)
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:query_groupinfo_by_groupids, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:query_groupinfo_by_groupids, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))
        except KeyError:
            self.logger.error('api:query_groupinfo_by_groupids, result:failure, ' +
                              'cause by: not found json-key from response' + '\n')
            raise Exception('api:query_groupinfo_by_groupids, result:failure! ' +
                            'cause by: not found json-key from response')

    def test_e_update_groups_by_id(self):
        """修改群组信息
        """
        self.logger.info('-------API:update groups by group-id--------------------')

        resp = []
        try:
            payload = {
                "groupname": "testrestgrp12",
                "desc": "server create group",
                "public": True,
                "maxusers": 300,
                "approval": True,
                "owner": "jianxincg013",
                "members": ["jianxincg014", "jianxincg015"]
            }
            payload1 = [
                {'username': 'jianxincg013', 'password': '123456'},
                {'username': 'jianxincg014', 'password': '123456'},
                {'username': 'jianxincg015', 'password': '123456'}
            ]
            resp = self.users_services.create_new_users(payload1)
            self.assertTrue(resp[0])
            self.logger.info('created user:' +
                             resp[1]['entities'][0]['username'] +
                             ',' + resp[1]['entities'][1]['username'] +
                             ',' + resp[1]['entities'][2]['username'] + ' as members')
            resp = self.groups_services.create_group(payload)
            self.assertTrue(resp[0])
            group_id = resp[1]['data']['groupid']
            self.logger.info('created chatgroup:' + group_id)
            payload3 = {
                'groupname': 'testrestgrp12',
                'description': 'update groupinfo',
                'maxusers': '300'
            }
            self.logger.info('update group by groupid[' + group_id + '] ...')
            resp = self.groups_services.update_groups_by_id(group_id, json.dumps(payload3))
            self.assertTrue(resp[0])
            self.logger.info('update_groups_by_id ok, response:' + json.dumps(resp[1]))

            resp = self.groups_services.remove_user_from_group(group_id, 'jianxincg014')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg014 from group:' + group_id)
            resp = self.groups_services.remove_user_from_group(group_id, 'jianxincg015')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg015 from group:' + group_id)
            resp = self.groups_services.delete_groups_by_id(group_id)
            self.assertTrue(resp[0])
            self.logger.info('deleted group:' + group_id)
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:update_groups_by_id, result:failure, ' +
                              'cause by: ' + json.dumps(resp[1]) + '\n')
            raise Exception('api:update_groups_by_id, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))
        except KeyError:
            self.logger.error('api:update_groups_by_id, result:failure, ' +
                              'cause by: not found json-key from response' + '\n')
            raise Exception('api:update_groups_by_id, result:failure! ' +
                            'cause by: not found json-key from response')

    def test_f_query_all_members_by_groupid(self):
        """查询群组成员
        """
        self.logger.info('--------API:query all member by group-id-------------------')

        resp = []
        try:
            payload = {
                "groupname": "testrestgrp12",
                "desc": "server create group",
                "public": True,
                "maxusers": 300,
                "approval": True,
                "owner": "jianxincg016",
                "members": ["jianxincg017", "jianxincg018"]
            }
            payload1 = [
                {'username': 'jianxincg016', 'password': '123456'},
                {'username': 'jianxincg017', 'password': '123456'},
                {'username': 'jianxincg018', 'password': '123456'}
            ]
            resp = self.users_services.create_new_users(payload1)
            self.assertTrue(resp[0])
            self.logger.info('created user:' +
                             resp[1]['entities'][0]['username'] +
                             ',' + resp[1]['entities'][1]['username'] +
                             ',' + resp[1]['entities'][2]['username'] + ' as members')
            resp = self.groups_services.create_group(payload)
            self.assertTrue(resp[0])
            group_id = resp[1]['data']['groupid']
            self.logger.info('created chatgroup:' + group_id)
            self.logger.info('query members of group[' + group_id + '] ...')
            resp = self.groups_services.query_all_members_by_groupid(group_id)
            self.assertTrue(resp[0])
            self.logger.info('query_all_members_by_groupid ok, response:' + json.dumps(resp[1]))

            resp = self.groups_services.remove_user_from_group(group_id, 'jianxincg017')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg017 from group:' + group_id)
            resp = self.groups_services.remove_user_from_group(group_id, 'jianxincg018')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg018 from group:' + group_id)
            resp = self.groups_services.delete_groups_by_id(group_id)
            self.assertTrue(resp[0])
            self.logger.info('deleted group:' + group_id)
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:query_all_members_by_groupid, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:query_all_members_by_groupid, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))
        except KeyError:
            self.logger.error('api:query_all_members_by_groupid, result:failure, ' +
                              'cause by: not found json-key from response' + '\n')
            raise Exception('api:query_all_members_by_groupid, result:failure! ' +
                            'cause by: not found json-key from response')

    def test_g_add_user_to_group_single(self):
        """群组增加成员[单个]
        """
        self.logger.info('--------API:add user to group----------------------')

        resp = []
        try:
            payload = {
                "groupname": "testrestgrp12",
                "desc": "server create group",
                "public": True,
                "maxusers": 300,
                "approval": True,
                "owner": "jianxincg019",
                "members": ["jianxincg020"]
            }
            payload1 = [
                {'username': 'jianxincg019', 'password': '123456'},
                {'username': 'jianxincg020', 'password': '123456'},
                {'username': 'jianxincg021', 'password': '123456'}
            ]
            resp = self.users_services.create_new_users(payload1)
            self.assertTrue(resp[0])
            self.logger.info('created user:' +
                             resp[1]['entities'][0]['username'] +
                             ',' + resp[1]['entities'][1]['username'] +
                             ',' + resp[1]['entities'][2]['username'] + ' as members')
            resp = self.groups_services.create_group(payload)
            self.assertTrue(resp[0])
            group_id = resp[1]['data']['groupid']
            self.logger.info('created chatgroup:' + group_id)
            username = 'jianxincg021'
            self.logger.info('add user[' + username + ']  into groupid[' + group_id + '] ...')
            resp = self.groups_services.add_user_to_group_single(group_id, username, {})
            self.assertTrue(resp[0])
            self.logger.info('add_user_to_group_single ok, response:'
                             + json.dumps(resp[1]))

            resp = self.groups_services.remove_user_from_group(group_id, 'jianxincg020')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg020 from group:' + group_id)
            resp = self.groups_services.remove_user_from_group(group_id, 'jianxincg021')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg021 from group:' + group_id)
            resp = self.groups_services.delete_groups_by_id(group_id)
            self.assertTrue(resp[0])
            self.logger.info('deleted group:' + group_id)
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:add_user_to_group_single, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:add_user_to_group_single, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))
        except KeyError:
            self.logger.error('api:add_user_to_group_single, result:failure, ' +
                              'cause by: not found json-key from response' + '\n')
            raise Exception('api:add_user_to_group_single, result:failure! ' +
                            'cause by: not found json-key from response')

    def test_h_add_user_to_group_batch(self):
        """群组增加成员[批量]
        """
        self.logger.info('--------API:add user to group batch-------------------')

        resp = []
        try:
            payload = {
                "groupname": "testrestgrp12",
                "desc": "server create group",
                "public": True,
                "maxusers": 300,
                "approval": True,
                "owner": "jianxincg022",
                "members": ["jianxincg023"]
            }
            payload1 = [
                {'username': 'jianxincg022', 'password': '123456'},
                {'username': 'jianxincg023', 'password': '123456'},
                {'username': 'jianxincg024', 'password': '123456'},
                {'username': 'jianxincg025', 'password': '123456'}
            ]
            resp = self.users_services.create_new_users(payload1)
            self.assertTrue(resp[0])
            self.logger.info('created user:' +
                             resp[1]['entities'][0]['username'] +
                             ',' + resp[1]['entities'][1]['username'] +
                             ',' + resp[1]['entities'][2]['username'] +
                             ',' + resp[1]['entities'][3]['username'] + ' as members')
            resp = self.groups_services.create_group(payload)
            self.assertTrue(resp[0])
            group_id = resp[1]['data']['groupid']
            self.logger.info('created chatgroup:' + group_id)
            payload3 = {'usernames': ['jianxincg024', 'jianxincg025']}
            self.logger.info('add user[jianxincg024, jianxincg025]  into groupid[' + group_id + '] ...')
            resp = self.groups_services.add_user_to_group_batch(group_id, payload3)
            self.assertTrue(resp[0])
            self.logger.info('add_user_to_group_batch ok, response:' +
                             json.dumps(resp[1]))

            resp = self.groups_services.remove_user_from_group(group_id, 'jianxincg023')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg023 from group:' + group_id)
            resp = self.groups_services.remove_user_from_group(group_id, 'jianxincg024')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg024 from group:' + group_id)
            resp = self.groups_services.remove_user_from_group(group_id, 'jianxincg025')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg025 from group:' + group_id)
            resp = self.groups_services.delete_groups_by_id(group_id)
            self.assertTrue(resp[0])
            self.logger.info('deleted group:' + group_id)
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:add_user_to_group_batch, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:add_user_to_group_batch, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))
        except KeyError:
            self.logger.error('api:add_user_to_group_batch, result:failure, ' +
                              'cause by: not found json-key from response' + '\n')
            raise Exception('api:add_user_to_group_batch, result:failure! ' +
                            'cause by: not found json-key from response')

    def test_i_remove_user_from_group(self):
        """群组移除成员
        """
        self.logger.info('--------API:remove user from group-----------------------')

        resp = []
        try:
            payload = {
                "groupname": "testrestgrp12",
                "desc": "server create group",
                "public": True,
                "maxusers": 300,
                "approval": True,
                "owner": "jianxincg026",
                "members": ["jianxincg027", 'jianxincg028']
            }
            payload1 = [
                {'username': 'jianxincg026', 'password': '123456'},
                {'username': 'jianxincg027', 'password': '123456'},
                {'username': 'jianxincg028', 'password': '123456'}
            ]
            resp = self.users_services.create_new_users(payload1)
            self.assertTrue(resp[0])
            self.logger.info('created user:' +
                             resp[1]['entities'][0]['username'] +
                             ',' + resp[1]['entities'][1]['username'] +
                             ',' + resp[1]['entities'][2]['username'] + ' as members')
            resp = self.groups_services.create_group(payload)
            self.assertTrue(resp[0])
            group_id = resp[1]['data']['groupid']
            self.logger.info('created chatgroup:' + group_id)
            username = 'jianxincg027'
            resp = self.groups_services.remove_user_from_group(group_id, username)
            self.assertTrue(resp[0])
            self.logger.info('remove_user_from_group ok, response:' + json.dumps(resp[1]))

            resp = self.groups_services.remove_user_from_group(group_id, 'jianxincg027')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg027 from group:' + group_id)
            resp = self.groups_services.delete_groups_by_id(group_id)
            self.assertTrue(resp[0])
            self.logger.info('deleted group:' + group_id)
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:remove_user_from_group, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:remove_user_from_group, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))
        except KeyError:
            self.logger.error('api:remove_user_from_group, result:failure, ' +
                              'cause by: not found json-key from response' + '\n')
            raise Exception('api:remove_user_from_group, result:failure! ' +
                            'cause by: not found json-key from response')

    def test_j_query_joined_groups_for_user(self):
        """查询用户所在的群
        """
        self.logger.info('---------API:query joined-groups for user----------------------')

        resp = []
        try:
            payload = {
                "groupname": "testrestgrp12",
                "desc": "server create group",
                "public": True,
                "maxusers": 300,
                "approval": True,
                "owner": "jianxincg029",
                "members": ["jianxincg030"]
            }
            payload1 = [
                {'username': 'jianxincg029', 'password': '123456'},
                {'username': 'jianxincg030', 'password': '123456'}
            ]
            resp = self.users_services.create_new_users(payload1)
            self.assertTrue(resp[0])
            self.logger.info('created user:' +
                             resp[1]['entities'][0]['username'] +
                             ',' + resp[1]['entities'][1]['username'] + ' as members')
            resp = self.groups_services.create_group(payload)
            self.assertTrue(resp[0])
            group_id = resp[1]['data']['groupid']
            self.logger.info('created chatgroup:' + group_id)
            username = 'jianxincg030'
            resp = self.groups_services.query_joined_groups_for_user(username)
            self.assertTrue(resp[0])
            self.logger.info('query_joined_groups_for_user ok, response:' +
                             json.dumps(resp[1]))

            resp = self.groups_services.remove_user_from_group(group_id, username)
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg030 from group:' + group_id)
            resp = self.groups_services.delete_groups_by_id(group_id)
            self.assertTrue(resp[0])
            self.logger.info('deleted group:' + group_id)
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:query_joined_groups_for_user, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:query_joined_groups_for_user, result:failure! ' +
                            'cause by: ' + str(resp[1]['error_description']))

    def test_k_delete_groups_by_id(self):
        """删除群组
        """
        self.logger.info('--------API:delete group by group-id-------------------------')

        resp = []
        try:
            payload = {
                "groupname": "testrestgrp12",
                "desc": "server create group",
                "public": True,
                "maxusers": 300,
                "approval": True,
                "owner": "jianxincg031",
                "members": ["jianxincg032"]
            }
            payload1 = [
                {'username': 'jianxincg031', 'password': '123456'},
                {'username': 'jianxincg032', 'password': '123456'}
            ]
            resp = self.users_services.create_new_users(payload1)
            self.assertTrue(resp[0])
            self.logger.info('created user:' +
                             resp[1]['entities'][0]['username'] +
                             ',' + resp[1]['entities'][1]['username'] + ' as members')
            resp = self.groups_services.create_group(payload)
            self.assertTrue(resp[0])
            group_id = resp[1]['data']['groupid']
            self.logger.info('created chatgroup:' + group_id)

            resp = self.groups_services.remove_user_from_group(group_id, 'jianxincg032')
            self.assertTrue(resp[0])
            self.logger.info('removed user:jianxincg032 from group:' + group_id)
            resp = self.groups_services.delete_groups_by_id(group_id)
            self.assertTrue(resp[0])
            self.logger.info('delete_groups_by_id ok, response:' +
                             json.dumps(resp[1]))
            self.logger.info('Over!\n')
        except AssertionError:
            self.logger.error('api:delete_groups_by_id, result:failure, ' +
                              'cause by: ' + str(resp[1]['error_description']) + '\n')
            raise Exception('api:delete_groups_by_id, result:failure!, '
                            'cause by: ' + str(resp[1]['error_description']))
        except KeyError:
            self.logger.error('api:delete_groups_by_id, result:failure, ' +
                              'cause by: not found json-key from response' + '\n')
            raise Exception('api:delete_groups_by_id, result:failure! '
                            'cause by: not found json-key from response')