## 环信服务器端实例代码　Python实现

本实例使用了[request](http://docs.python-requests.org/en/latest/)类库来实现HTTP请求调用环信的REST API, 在运行本示例代码之前, 请先参考[这里](http://docs.python-requests.org/en/latest/user/install/)安装该python类库.


##### 导入到[Pycharm](https://www.jetbrains.com/pycharm/download/)中:
> 1. Clone project :
        $ git clone git@github.com:easemob/emchat-server-examples.git
> 2. Open :
        File --> Open --> Select the python project named[emchat-server-python] --> Click [OK]

#### 修改示例代码中的app相关参数[appKey, client_id, client_secret]
	$ cd emchat-server-python
    $ vim easemob/emchat/utils/confs.py

##### 在命令行中运行测试
	$ cd emchat-server-python
	$ python main.py
	or
	$ chmod +x main.py && ./main.py

##### 安装为一个python　module
	$ cd emchat-server-python
    $ python setup.py install