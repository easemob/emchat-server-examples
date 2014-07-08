#!/usr/bin/python
# -*- coding: utf-8 -*- 
__author__ = 'stliu'

import requests
import json
from time import time
from requests.auth import AuthBase
import string
import random

JSON_HEADER = {'content-type': 'application/json'}
# EASEMOB_HOST = "http://localhost:8080"#
EASEMOB_HOST = "https://a1.easemob.com"

DEBUG = False

def id_generator(size=6, chars=string.ascii_uppercase + string.digits):
    """用来随机生成用户名, 仅仅用来测试用的"""
    return ''.join(random.choice(chars) for _ in range(size))

def parse_appkey(appkey):
    """解析appkey, 从中得到org和app, 注意, appkey的规则是 {org}#{app}"""
    return tuple(appkey.split('#'))

def post(url, payload, auth=None):
    r = requests.post(url, data=json.dumps(payload), headers=JSON_HEADER, auth=auth)
    return http_result(r)


def get(url, auth=None):
    r = requests.get(url, headers=JSON_HEADER, auth=auth)
    return http_result(r)


def delete(url, auth=None):
    r = requests.delete(url, headers=JSON_HEADER, auth=auth)
    return http_result(r)


def http_result(r):
    if DEBUG:
        error_log = {
                    "method": r.request.method,
                    "url": r.request.url,
                    "request_header": dict(r.request.headers),
                    "response_header": dict(r.headers),
                    "response": r.text
                }
        if r.request.body:
            error_log["payload"] = r.request.body
        print json.dumps(error_log)

    if r.status_code == requests.codes.ok:
        return True, r.json()
    else:
        return False, r.text


class Token:
    """表示一个登陆获取到的token对象"""
    def __init__(self, token, exipres_in):
        self.token = token
        self.exipres_in = exipres_in + int(time())
        
    def is_not_valid(self):
        """这个token是否还合法, 或者说, 是否已经失效了, 这里我们只需要
        检查当前的时间, 是否已经比或者这个token的时间过去了exipreis_in秒
        
        即  current_time_in_seconds < (expires_in + token_acquired_time)
        """
        return time() > self.exipres_in

class EasemobAuth(AuthBase):
    """环信登陆认证的基类"""
    
    def __call__(self, r):
        r.headers['Authorization'] = 'Bearer ' + self.get_token()
        return r  
          
    def get_token(self):
        """在这里我们先检查是否已经获取过token, 并且这个token有没有过期"""
        if (self.token is None) or (self.token.is_not_valid()):
            self.token = self.acquire_token() #refresh the token
        return self.token.token
        
    def acquire_token(self):
        """真正的获取token的方法, 返回值是一个我们定义的Token对象
            这个留给子类去实现
        """
        pass
        
class AppClientAuth(EasemobAuth):
    """使用app的client_id和client_secret来获取app管理员token"""
    def __init__(self, org, app, client_id, client_secret):
        super(AppClientAuth, self).__init__()
        self.client_id = client_id
        self.client_secret = client_secret
        self.url = EASEMOB_HOST+("/%s/%s/token" % (org, app))
        self.token = None
        
    def acquire_token(self):
        """
        使用clieng_id / client_secret来获取token, 具体的REST API为
        
        POST /{org}/{app}/token {'grant_type':'client_credentials', 'client_id':'xxxx', 'client_secret':'xxxxx'}
        """
        payload = {'grant_type':'client_credentials', 'client_id': self.client_id, 'client_secret': self.client_secret}
        success, result = post(self.url, payload)
        if success:
            return Token(result['access_token'], result['expires_in'])
        else:
            # throws exception
            pass

class AppAdminAccountAuth(EasemobAuth):
    """使用app的管理员账号和密码来获取token"""
    def __init__(self, org, app, username, password):
        super(AppAdminAccountAuth, self).__init__()
        self.username = username
        self.password = password
        self.url = EASEMOB_HOST+("/%s/%s/token" % (org, app))
        self.token = None
        
    def acquire_token(self):
        """
        使用 username / password 来获取token, 具体的REST API为
        
        POST /{org}/{app}/token {'grant_type':'password', 'username':'xxxx', 'password':'xxxxx'}
        
        这里和上面使用client_id不同的是, grant_type的类型是password, 然后还需要提供username和password
        """
        payload = {'grant_type':'password', 'username': self.username, 'password': self.password}
        success, result = post(self.url, payload)
        if success:
            return Token(result['access_token'], result['expires_in'])
        else:
            # throws exception
            pass
                        

class OrgAdminAccountAuth(EasemobAuth):
    """使用org的管理员账号和密码来获取token, 
    和上面不同的是, 这里获取的是整个org的管理员账号, 
    所以并没有appkey的概念
    
    并且, 因为没有appkey的概念, 所以, URL也不相同, 
    这里使用的URL是 https://a1.easemob.com/management/token
    
    而app级别的token都是从 https://a1.easemob.com/{org}/{app}/token
    这个URL去获取的
    """
    def __init__(self, username, password):
        super(OrgAdminAccountAuth, self).__init__()
        self.username = username
        self.password = password
        self.url = EASEMOB_HOST+"/management/token"
        self.token = None
        
    def acquire_token(self):
        """
        使用 username / password 来获取token, 具体的REST API为
        
        POST /management/token {'grant_type':'password', 'username':'xxxx', 'password':'xxxxx'}
        """
        payload = {'grant_type':'password', 'username': self.username, 'password': self.password}
        success, result = post(self.url, payload)
        if success:
            return Token(result['access_token'], result['expires_in'])
        else:
            # throws exception
            pass



def register_new_user(org, app, auth, username, password):
    """注册新的app用户
    POST /{org}/{app}/users {"username":"xxxxx", "password":"yyyyy"}
    """
    payload = {"username":username, "password":password}
    url = EASEMOB_HOST+("/%s/%s/users" % (org, app))
    return post(url, payload, auth)
    
def delete_user(org, app, auth, username):
    """删除app用户
    DELETE /{org}/{app}/users/{username}
    """
    url = EASEMOB_HOST+("/%s/%s/users/%s" % (org, app, username))
    return post(url, {}, auth)

def send_file(org, app, auth, file_path):
    """上传文件
    上传文件
    curl --verbose --header "Authorization: Bearer YWMtz1hFWOZpEeOPpcmw1FB0RwAAAUZnAv0D7y9-i4c9_c4rcx1qJDduwylRe7Y" \
    --header "restrict-access:true" --form file=@/Users/stliu/a.jpg \
    http://a1.easemob.com/easemob-demo/chatdemoui/chatfiles
    """
    url = EASEMOB_HOST+("/%s/%s/chatfiles" % (org, app))
    # files = {'file': open(file_path, 'rb')}
    files = {'file': ('report.xls', open(file_path, 'rb'), 'image/jpeg', {'Expires': '0'})}
    r = requests.post(url, files=files,  auth=auth)
    return http_result(r)
 

if __name__ == '__main__':
    #测试数据
    f = file("../info.json")
    s = json.load(f)
    appkey = s['appkey']
    org, app = parse_appkey(appkey)
    org_admin_username = s['admin']['username']
    org_admin_password = s['admin']['password']
    client_id = s['app']['credentials']['client_id']
    client_secret = s['app']['credentials']['client_secret']
    app_admin_username = s['app']['admin']['username']
    app_admin_password = s['app']['admin']['password']
    # org_admin的认证方式
    org_admin_auth = OrgAdminAccountAuth(org_admin_username, org_admin_password)
    # 获取org管理员的token
    print "Get org admin token: " + org_admin_auth.get_token()
    # 获取app管理员的token
    app_admin_auth = AppAdminAccountAuth(org, app, app_admin_username, app_admin_password)
    print "Get app admin token:" + app_admin_auth.get_token()
    # 通过client id和secret来获取app管理员的token
    app_client_auth = AppClientAuth(org, app, client_id, client_secret)
    print "Get app admin token with client id/secret: " + app_client_auth.get_token()
    
    print "now let's register some users...."
    # app_users = []
   #  for i in range(10):
   #      username = id_generator()
   #
   #      password = '123456'
   #      success, result = register_new_user(org, app, app_admin_auth, username, password)
   #      if success:
   #          print "registered new user %s in appkey[%s]" % (username, appkey)
   #          app_users.append(username)
   #      else:
   #          print "failed to register new user %s in appkey[%s]" % (username, appkey)
   #
   #  print "now let's delete users just created, this time we're using app_client_auth"
   #
   #  for username in app_users:
   #      success, result = delete_user(org, app,app_client_auth, username)
   #      if success:
   #          print "user %s is deleted"
   #      else:
   #          print "failed to delete user %s"
    success, result = send_file(org, app, app_client_auth, '../zjg.jpg')
    print result
   

