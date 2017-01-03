## Server Sample Source Code

Master is a developing branch. Download release branch for stable version.

The project can be built using Gradle and Maven, which we recommend, or be compiled from command line. 


## Maven

This project upports build with [Maven](http://maven.apache.org), please see maven documentation and pom.xml for implementation details

## Gradle

You can build with IDE using [Gradle](http://gradle.org). For example,


## Installed the Java SDK

You can download and install the most recent version of the [Java SDK](http://java.sun.com/javase/downloads/index.jsp).


## Installed your favorite Java IDE

You can use IntelliJ, Eclipse, NetBeans or any Java IDE you prefer to the run the project.

### Eclipse

#### Unix or Mac
 
    $ gradle eclipse

#### Windows

Run the following command, it'll generate necessary Eclipse files, then import via Eclipse

    > gradlew.bat eclipse

### Intellij IDEA

#### Unix or Mac

	$ gradle idea

#### Windows

Run the following command and open the project from Intellij IDEA

    > gradlew.bat idea
	

## Build from Command line

#### Windows

	> gradlew.bat clean compile
	
## Dependencies
	
 - package com.hyphenate.server.example.jersey is using Jersey 2.15. Java 7 or later is required.
 - package com.hyphenate.server.example.httpclient is using Httpclient 4.3.3. Java 1.5 or later is required.
 
 - Install JUnit library (ex. junit:junit:4.12)
 
### Get all the dependencies

We recommend using [maven](http://maven.apache.org) or [gradle](http://gradle.org) to build server components. 

#### Windows

If you're not using the tools listed above, you can manual manage the .jar package by running the following command.

    > gradlew.bat distZip

That will create a zip file, hyphenate-server-example.zip, under the folder _build/distributions_, which includes all the dependency    
 

## Configuration

Update Hyphenate app properties in the file, `config.properties`.

```java
API_ORG = hyphenatedemo
API_APP = demo
APP_CLIENT_ID = YXA68E7DkM4uEeaPwTPbScypMA
APP_CLIENT_SECRET = YXA63_RZdbtXQB9QZsizSCgMC70_4Rs
```