@echo off

rem -- Lipsion
color 1f
:menu
echo   ________________________________________________________________
echo  ^|                                                                ^|
echo  ^|                     Maven  -  �������                         ^|
echo  ^|                                                                ^|
echo  ^|  0 - clean-package             1 - mvn install                 ^|
echo  ^|  2 - mvn deploy                                                ^|
echo  ^|________________________________________________________________^|
:input
set /p input=-^> ��ѡ��: 

if "%input%"== "0" goto clean-package
if "%input%"== "1" goto install
if "%input%"== "2" goto deploy
goto end

:clean-package
echo  # �������벢��� #
mvn clean package -U -Dmaven.test.skip=true &&pause&&cls&& call compile.bat

:install
echo  # ��װ���زֿ� #
mvn install -Dmaven.test.skip=true &&pause&&cls&& call compile.bat

:deploy
echo  # ���� #
mvn deploy -Dmaven.test.skip=true &&pause&&cls&& call compile.bat

:end
echo ����
prompt
popd