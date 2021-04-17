FROM openjdk:8-jdk-alpine
RUN cd /usr/local
RUN mkdir seven
WORKDIR /usr/local/seven
COPY . ./
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN chmod +x /usr/local/seven/config.sh
ENTRYPOINT ["/usr/local/seven/config.sh"]