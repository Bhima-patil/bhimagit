#!/bin/bash

sudo su - tomcat

# Assuming Tomcat is installed in /opt/apache-tomcat-10.1.18, adjust the path accordingly
TOMCAT_HOME="/opt/apache-tomcat-10.1.18"


# Stop Tomcat if it is already running (to ensure a clean start)
# sudo $TOMCAT_HOME/bin/shutdown.sh


# Wait for Tomcat to fully stop (adjust the sleep duration based on your application's shutdown time)
# sleep 10

# Start Tomcat

$TOMCAT_HOME/bin/startup.sh

# Wait for Tomcat to fully start (adjust the sleep duration based on your application's startup time)
sleep 30

# Additional commands or configurations after Tomcat has started can be added here
# For example, you might want to tail the catalina.out log file for debugging:
# tail -f $TOMCAT_HOME/logs/catalina.out