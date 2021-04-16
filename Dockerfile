FROM openjdk:11-jdk-alpine

ARG version

RUN mkdir /reece \
 && mkdir /reece/service

COPY scripts/start-server.sh                        /reece/service/start-server.sh
COPY build/libs/address-book-$version.jar      /reece/service/service.jar

RUN chmod +x /reece/service/start-server.sh
WORKDIR /reece/service

EXPOSE 8080

CMD ["./start-server.sh"]
