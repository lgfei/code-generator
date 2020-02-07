## 环境变量
server.port=8080
server.servlet.context-path=/${my.artifactId}-api
spring.profiles.active=default,dev

## 打印sql日志
logging.level.${my.parentPackage}.${package.ModuleName}.core.mapper=DEBUG

## 项目个性配置
project.model-package=${my.parentPackage}.${package.ModuleName}.common
