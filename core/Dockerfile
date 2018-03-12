FROM maven:3.2-jdk-8

WORKDIR /data

ADD ./pom.xml ./

RUN mvn clean install

ADD . ./
