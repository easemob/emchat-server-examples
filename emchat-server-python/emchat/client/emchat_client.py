#!/usr/bin/python
# -*- coding: utf-8 -*-
__author__ = 'Lynch Lee'


from emchat.utils.types import *
from emchat.services.emchat_service import *
from emchat.utils.confs import *


def get_instance(service_type):
    if service_type == service_users:
        return EMIMUsersService(org, app)
    if service_type == service_chatfiles:
        return EMChatFilesService(org, app)
    if service_type == service_chatgroups:
        return EMChatGroupsService(org, app)
    if service_type == service_chatmessages:
        return EMChatMessagesService(org, app)
    if service_type == service_messages:
        return EMMessagesService(org, app)


