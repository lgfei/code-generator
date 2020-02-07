<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" 
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
	    <groupId>com.lgfei</groupId>
	    <artifactId>betterme-framework</artifactId>
	    <version>${my.frameworkVersion}</version>
	    <relativePath>../betterme-framework</relativePath>
    </parent>
  
    <artifactId>${my.artifactId}</artifactId>
    <packaging>pom</packaging>
    <name>${my.artifactId}</name>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <framework-version>${my.frameworkVersion}</framework-version>
    </properties>

    <dependencies>
  	    <dependency>
  	        <groupId>com.lgfei</groupId>
  	        <artifactId>betterme-framework-api</artifactId>
  	        <version>${r'${framework-version}'}</version>
        </dependency>
            
    </dependencies>
  
    <modules>
        <module>${my.artifactId}-api</module>
        <module>${my.artifactId}-common</module>
        <module>${my.artifactId}-core</module>
    </modules>
    
</project>