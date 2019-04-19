<?xml version="1.0"?>
<project xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd" xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <modelVersion>4.0.0</modelVersion>
  <parent>
    <groupId>${my.groupId}</groupId>
    <artifactId>${my.artifactId}</artifactId>
    <version>0.0.1-SNAPSHOT</version>
  </parent>
  
  <artifactId>${my.artifactId}-core</artifactId>
  <name>${my.artifactId}-core</name>
  <url>http://maven.apache.org</url>
  
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>
  
  <dependencies>
    <dependency>
      <groupId>${my.groupId}</groupId>
      <artifactId>${my.artifactId}-model</artifactId>
      <version>${r'${framework-version}'}</version>
    </dependency>
  </dependencies>
  
</project>
