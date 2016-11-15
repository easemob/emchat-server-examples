#!/usr/bin/python
# -*- coding: utf-8 -*-
__author__ = 'Lynch Lee'


import logging


filename = './emchat-py.log'


class Logger(object):

    @staticmethod
    def get_logger(name):

        # create logger
        u_logger = logging.getLogger(name)
        # set log file path
        logging.basicConfig(filename=filename)
        # set level
        u_logger.setLevel(logging.INFO)

        # create console handler and set level to debug
        ch = logging.StreamHandler()
        ch.setLevel(logging.DEBUG)
        # create formatter
        formatter = logging.Formatter('%(name)s - %(asctime)s - %(levelname)s - %(message)s',
                                      datefmt='%Y/%m/%d %I:%M:%S %p')
        # add formatter to ch
        ch.setFormatter(formatter)
        # add ch to logger
        u_logger.addHandler(ch)

        return u_logger