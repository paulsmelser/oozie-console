@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  oozie-console-gradle startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

@rem Add default JVM options here. You can also use JAVA_OPTS and OOZIE_CONSOLE_GRADLE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windowz variants

if not "%OS%" == "Windows_NT" goto win9xME_args
if "%@eval[2+2]" == "4" goto 4NT_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*
goto execute

:4NT_args
@rem Get arguments from the 4NT Shell from JP Software
set CMD_LINE_ARGS=%$

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\oozie-console-1.0.0-SNAPSHOT.jar;%APP_HOME%\lib\spring-boot-starter-web-1.1.6.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-actuator-1.1.6.RELEASE.jar;%APP_HOME%\lib\oozie-rest-client-1.0.0.jar;%APP_HOME%\lib\oozie-web-1.0.0.jar;%APP_HOME%\lib\xstream-1.4.7.jar;%APP_HOME%\lib\jersey-client-1.18.3.jar;%APP_HOME%\lib\SimpleMapper-0.0.2-RELEASE.jar;%APP_HOME%\lib\genson-0.99.jar;%APP_HOME%\lib\gson-2.3.1.jar;%APP_HOME%\lib\spring-boot-starter-thymeleaf-1.1.6.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-1.1.6.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-tomcat-1.1.6.RELEASE.jar;%APP_HOME%\lib\jackson-databind-2.3.4.jar;%APP_HOME%\lib\hibernate-validator-5.0.3.Final.jar;%APP_HOME%\lib\spring-core-4.0.7.RELEASE.jar;%APP_HOME%\lib\spring-web-4.0.7.RELEASE.jar;%APP_HOME%\lib\spring-webmvc-4.0.7.RELEASE.jar;%APP_HOME%\lib\spring-boot-actuator-1.1.6.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-aop-1.1.6.RELEASE.jar;%APP_HOME%\lib\bootstrap-3.2.0.jar;%APP_HOME%\lib\xmlpull-1.1.3.1.jar;%APP_HOME%\lib\xpp3_min-1.1.4c.jar;%APP_HOME%\lib\jersey-core-1.18.3.jar;%APP_HOME%\lib\thymeleaf-spring4-2.1.3.RELEASE.jar;%APP_HOME%\lib\thymeleaf-layout-dialect-1.2.5.jar;%APP_HOME%\lib\spring-boot-1.1.6.RELEASE.jar;%APP_HOME%\lib\spring-boot-autoconfigure-1.1.6.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-logging-1.1.6.RELEASE.jar;%APP_HOME%\lib\snakeyaml-1.13.jar;%APP_HOME%\lib\tomcat-embed-core-7.0.55.jar;%APP_HOME%\lib\tomcat-embed-el-7.0.55.jar;%APP_HOME%\lib\tomcat-embed-logging-juli-7.0.55.jar;%APP_HOME%\lib\jackson-annotations-2.3.0.jar;%APP_HOME%\lib\jackson-core-2.3.4.jar;%APP_HOME%\lib\validation-api-1.1.0.Final.jar;%APP_HOME%\lib\jboss-logging-3.1.1.GA.jar;%APP_HOME%\lib\classmate-1.0.0.jar;%APP_HOME%\lib\spring-aop-4.0.7.RELEASE.jar;%APP_HOME%\lib\spring-beans-4.0.7.RELEASE.jar;%APP_HOME%\lib\spring-context-4.0.7.RELEASE.jar;%APP_HOME%\lib\spring-expression-4.0.7.RELEASE.jar;%APP_HOME%\lib\aspectjrt-1.8.2.jar;%APP_HOME%\lib\aspectjweaver-1.8.2.jar;%APP_HOME%\lib\jquery-1.11.1.jar;%APP_HOME%\lib\thymeleaf-2.1.3.RELEASE.jar;%APP_HOME%\lib\jcl-over-slf4j-1.7.7.jar;%APP_HOME%\lib\jul-to-slf4j-1.7.7.jar;%APP_HOME%\lib\log4j-over-slf4j-1.7.7.jar;%APP_HOME%\lib\logback-classic-1.1.2.jar;%APP_HOME%\lib\aopalliance-1.0.jar;%APP_HOME%\lib\ognl-3.0.6.jar;%APP_HOME%\lib\javassist-3.16.1-GA.jar;%APP_HOME%\lib\unbescape-1.0.jar;%APP_HOME%\lib\logback-core-1.1.2.jar;%APP_HOME%\lib\slf4j-api-1.7.7.jar

@rem Execute oozie-console-gradle
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %OOZIE_CONSOLE_GRADLE_OPTS%  -classpath "%CLASSPATH%" org.smelser.oozie.Application %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable OOZIE_CONSOLE_GRADLE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%OOZIE_CONSOLE_GRADLE_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
