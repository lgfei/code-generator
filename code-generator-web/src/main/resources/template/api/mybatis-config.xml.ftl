<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration  
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"  
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!-- mybatis基础配置 -->
<configuration>
  <!-- 设置运行参数 -->
  <settings>
	<!-- 是否允许单一语句返回多结果集（需要兼容驱动）。 默认值true -->
	<setting name="multipleResultSetsEnabled" value="true" />
  </settings>

  <!-- 基础数据类型别名映射 -->
  <typeAliases>
    <typeAlias alias="String" type="java.lang.String" />
	<typeAlias alias="Integer" type="java.lang.Integer" />
	<typeAlias alias="Long" type="java.lang.Long" />
	<typeAlias alias="HashMap" type="java.util.HashMap" />
	<typeAlias alias="ArrayList" type="java.util.ArrayList" />
	<typeAlias alias="LinkedList" type="java.util.LinkedList" />
	<typeAlias alias="HashSet" type="java.util.HashSet" />
	<typeAlias alias="TreeSet" type="java.util.TreeSet" />
  </typeAliases>
  
  <!-- 配置映射文件：用来配置sql语句和结果集类型等 -->
  <mappers>
    <#if entityList?exists >
    <#list entityList as entity>
      <mapper resource="mapper/${entity}Mapper.xml"/>
    </#list>
    </#if>
  </mappers>
  
</configuration>