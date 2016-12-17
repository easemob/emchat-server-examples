## Server Sample Source Code

Master is a developing branch. Download release branch for stable version.

The project can be built using Gradle and Maven, which we recommend, or be compiled from command line. 

## Maven

This project upports build with [Maven](http://maven.apache.org), please see maven documentation and pom.xml for implementation details

## Gradle

You can build with IDE using [Gradle](http://gradle.org). For example,

### Eclipse

#### Unix
 
    $ gradle eclipse

#### Windows

Run the following command, it'll generate necessary Eclipse files, then import via Eclipse

    > gradlew.bat eclipse

### Intellij IDEA

#### Unix

	$ gradle idea

#### Windows

Run the following command and open the project from Intellij IDEA

    > gradlew.bat idea
	

## Build from Command line

#### Windows

	> gradlew.bat clean compile
	
## Dependencies
	
 - package com.easemob.server.example.jersey is using Jersey 2.15. Java 7 or later is required.
 - package com.easemob.server.example.httpclient is using Httpclient 4.3.3. Java 1.5 or later is required.
 
### Get all the dependencies

We recommend using [maven](http://maven.apache.org) or [gradle](http://gradle.org) to build server components. 

#### Windows

If you're not using the tools listed above, you can manual manage the .jar package by running the following command.

    > gradlew.bat distZip

That will create a zip file, easemob-server-example.zip, under the folder _build/distributions_, which includes all the dependency    
 
