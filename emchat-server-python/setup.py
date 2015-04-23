#!/usr/bin/python
# -*- coding: utf-8 -*-
__author__ = 'Lynch Lee'


import os
import sys


try:
    from setuptools import setup
    from setuptools.command.test import test as TestCommand

    class PyTest(TestCommand):
        def finalize_options(self):
            TestCommand.finalize_options(self)
            self.test_args = []
            self.test_suite = True

        def run_tests(self):
            # import here, because outside the eggs aren't loaded
            import pytest
            errno = pytest.main(self.test_args)
            sys.exit(errno)

except ImportError:

    from distutils.core import setup

    def PyTest(x): x

f = open(os.path.join(os.path.dirname(__file__), 'README.md'))
long_description = f.read()
f.close()

f = open(os.path.join(os.path.dirname(__file__), 'VERSION'))
_version_ = f.read()
f.close()


setup(
    name='emchat',
    version=_version_,
    description='Python Client for Easemob REST API Access',
    long_description=long_description,
    url='https://github.com/easemob/emchat-server-examples/tree/master/emchat-server-python',
    author='Lynch Lee',
    author_email='lynch.lee9527@gmail.com',
    maintainer='Easemob[http://www.easemob.com]',
    maintainer_email='support@easemob.com',
    keywords=['easemob', 'rest', 'api', 'emchat', 'im', 'usergrid'],
    packages=['emchat'],
    tests_require=['pytest>=2.7.0'],
    cmdclass={'test': PyTest}
)