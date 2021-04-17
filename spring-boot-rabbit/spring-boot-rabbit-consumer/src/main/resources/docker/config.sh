#!/bin/sh

sed -i "s#@active@#$active#g" /usr/local/seven/application.properties
sed -i "s#@virtual_host@#$virtual_host#g" /usr/local/seven/application.properties

java -Djava.security.egd=file:/dev/./urandom -Xms500m -Xmx500m -jar spring-boot-rabbit-consumer-1.0.0.jar
