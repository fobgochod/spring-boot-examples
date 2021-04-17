#!/bin/bash

docker run -idt --restart=always --privileged=true --name rabbitConsumer -p 8081:22221 \
-e active='prod' \
-e virtual-host='fobgocod' \
spring-boot-rabbit-consumer:1.0.0

docker run -idt --restart=always --privileged=true --name rabbitProducer -p 8080:22220 \
-e active='prod' \
-e virtual-host='fobgocod' \
spring-boot-rabbit-producer:1.0.0