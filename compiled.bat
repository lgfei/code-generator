@echo off

rem -- Lipsion
color 1f
:menu
echo   ________________________________________________________________
echo  ^|                                                                ^|
echo  ^|                     Maven  -  控制面板                         ^|
echo  ^|                                                                ^|
echo  ^|  0 - clean-package             1 - mvn install                 ^|
echo  ^|  2 - mvn deploy                                                ^|
echo  ^|________________________________________________________________^|
:input
set /p input=-^> 请选择: 

if "%input%"== "0" goto clean-package
if "%input%"== "1" goto install
if "%input%"== "2" goto deploy
goto end

:clean-package
echo  # 消除编译并打包 #
mvn clean package -U -Dmaven.test.skip=true &&pause&&cls&& call compile.bat

:install
echo  # 安装本地仓库 #
mvn install -Dmaven.test.skip=true &&pause&&cls&& call compile.bat

:deploy
echo  # 发布 #
mvn deploy -Dmaven.test.skip=true &&pause&&cls&& call compile.bat

:end
echo 结束
prompt
popd