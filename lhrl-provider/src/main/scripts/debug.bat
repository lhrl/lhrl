@echo off & setlocal enabledelayedexpansion

title crm-sale-provider:debug

set LIB_JARS=""
cd ..\lib
for %%i in (*) do set LIB_JARS=!LIB_JARS!;..\lib\%%i
cd ..\bin

java -Xms64m -Xmx1024m -XX:MaxPermSize=64M -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=y -classpath ..\conf\dubbo;%LIB_JARS% com.alibaba.dubbo.container.Main
goto end

:end
pause