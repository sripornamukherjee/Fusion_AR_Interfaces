@echo on

@echo off

:start
set inputFolder=C:\FileIO\Receipt\input
set extension=csv

IF EXIST %inputFolder%\*.%extension% GOTO exists

goto end

:exists

echo EXISTS
echo.

### java -Dspring.batch.job.names=importReceiptLockJob -Dspring.config.location=classpath:application.properties -jar fusionintegration-0.0.1-SNAPSHOT.jar

goto start
:end

PAUSE