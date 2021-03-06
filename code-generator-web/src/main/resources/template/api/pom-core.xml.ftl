<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" 
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>${my.groupId}</groupId>
        <artifactId>${my.artifactId}</artifactId>
        <version>${my.frameworkVersion}</version>
    </parent>
  
    <artifactId>${my.artifactId}-core</artifactId>
    <name>${my.artifactId}-core</name>
  
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
  
    <dependencies>
        <dependency>
            <groupId>${my.groupId}</groupId>
            <artifactId>${my.artifactId}-common</artifactId>
            <version>${r'${framework-version}'}</version>
        </dependency>
        
    </dependencies>
  
</project>
