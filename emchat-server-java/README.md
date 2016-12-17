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
    
### Notes：
 - 1. package com.easemob.server.example.jersey下采用Jersey2.15实现, 需要java7或更高版本支持;
 - 2. package com.easemob.server.example.httpclient下采用Httpclient4.3.3实现，需要Java1.5或更高版本支持.
 - 3. Master分支是开发分支，请慎重直接下载使用。如有需要请下载已经Release的版本。
