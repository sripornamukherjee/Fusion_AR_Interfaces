@echo on

@echo off

echo.
echo.


java -Dspring.batch.job.names=importUserJob -Dspring.config.location=classpath:application.properties -jar fusionintegration-0.0.1-SNAPSHOT.jar

:EOF

PAUSE