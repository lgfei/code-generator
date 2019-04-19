<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <parent>
	  <groupId>com.lgfei.betterme</groupId>
	  <artifactId>betterme-framework</artifactId>
	  <version>0.0.1-SNAPSHOT</version>
  </parent>
  
  <artifactId>${my.artifactId}</artifactId>
  <packaging>pom</packaging>
  <name>${my.artifactId}</name>
  <url>http://maven.apache.org</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <framework-version>0.0.1-SNAPSHOT</framework-version>
  </properties>

  <dependencies>
  	<dependency>
  	  <groupId>com.lgfei.betterme</groupId>
  	  <artifactId>betterme-framework-api</artifactId>
  	  <version>${r'${framework-version}'}</version>
  	</dependency>
  	<dependency>
  	  <groupId>com.lgfei.betterme</groupId>
  	  <artifactId>betterme-framework-core</artifactId>
  	  <version>${r'${framework-version}'}</version>
  	</dependency>
  	<dependency>
  	  <groupId>com.lgfei.betterme</groupId>
  	  <artifactId>betterme-framework-model</artifactId>
  	  <version>${r'${framework-version}'}</version>
  	</dependency>
  	<dependency>
  	  <groupId>com.lgfei.betterme</groupId>
  	  <artifactId>betterme-framework-util</artifactId>
  	  <version>${r'${framework-version}'}</version>
  	</dependency>
  </dependencies>
  
  <modules>
    <module>${my.artifactId}-api</module>
    <module>${my.artifactId}-core</module>
    <module>${my.artifactId}-model</module>
  </modules>
</project>