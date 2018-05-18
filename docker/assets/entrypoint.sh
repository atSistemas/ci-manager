#!/bin/sh
printf "********** STARTING SPRINGBOOT APP **********\n"

if [ "$DEBUG" = true ]; then
  printf "Running the application in debug mode.\n"
  JAVA_OPTS="$JAVA_OPTS -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n"
fi

# Improve startup time by using less complex random seed
JAVA_OPTS="$JAVA_OPTS -Djava.security.egd=file:/dev/./urandom"

#JAVA_COMMAND="java $JAVA_OPTS -jar /app/spring-boot.jar $SPRINGBOOT_OPTS"
JAVA_COMMAND="java $JAVA_OPTS -jar /app/spring-boot.jar"

printf "JAVA_OPTS: $JAVA_OPTS\n"
printf "SPRINGBOOT_OPTS: $SPRINGBOOT_OPTS\n"
printf "JAVA_COMMAND: $JAVA_COMMAND\n"
printf "ENVIRONMENT:\n"
printenv
printf "*********************************************\n"

exec $JAVA_COMMAND
