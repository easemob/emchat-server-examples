## 环信服务器端实例代码

本项目支持使用[Maven](http://maven.apache.org)构建, 具体使用方式请参考maven的使用文档和pom.xml



如果使用[Gradle](http://gradle.org)进行构建, 可以直接导入到IDE中, 例如



### 导入到eclipse中:

windows下运行

    gradlew.bat eclipse

unix-like下运行

    ./gradlew eclipse

这个命令会生成eclipse的项目文件, 然后可以通过eclipse的import工具导入

### 导入到Intellij中:

windows下运行

    gradlew.bat idea

unix-like下运行

    ./gradlew idea

然后直接在Intellij中打开这个项目

### 获取本项目的所有依赖

我们建议您使用 [maven](http://maven.apache.org) 或者 [gradle](http://gradle.org) 来构建您的服务器项目,
如果您没有使用上面的工具, 而是自己手工管理所依赖的jar包的话, 可以使用下面的命令来得到本项目使用到的jar包

windows下运行

    gradlew.bat distZip

unix-like下运行

    ./gradlew distZip

这个会在 _build/distributions_ 目录下生成一个 easemob-server-example.zip 文件, 里面包含了本项目的所有依赖

### 直接编译

windows下运行

    gradlew.bat clean compile

unix-like下运行

    ./gradlew clean compile

### 生成证书库

##### step 1. 下载证书文件
打开浏览器，输入网址https://a1.easemob.com/status ，点击网址框左侧的锁形状，点击`详细信息`->`View certificate`->`复制到文件`，直到导出证书文件。

##### step 2. 使用keytool工具创建证书库

    Keytool –genkey –alias "certificate" –keyalg "RSA" –keystore "test.keystore"
    
这里的test.keystore是生成的证书库,可以指定生成目录比如d:\test.keystore,这样就可以在d盘找到刚才生成的证书库了

alias：证书名称

keyalg：生成证书采用的算法，如RSA

keystore：证书库名称

##### step 3. 将证书导入到证书库中
    Keytool –import –keystore test.keystore –file C:\Users\EaseMob\Desktop\certificate.cer

### Notes：
 - 1. package com.easemob.server.example.jersey下采用Jersey2.15实现, 需要java7或更高版本支持;
 - 2. package com.easemob.server.example.httpclient下采用Httpclient4.3.3实现，需要Java1.5或更高版本支持.
 - 3. Master分支是开发分支，请慎重直接下载使用。如有需要请下载已经Release的版本。
