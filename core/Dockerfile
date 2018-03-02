FROM maven:3.2-jdk-8

WORKDIR /data

ADD ./pom.xml ./

RUN mvn clean install

ADD . ./

CMD ["java", "-jar", "target/tutorial-1.0-SNAPSHOT.jar", "server", "configuration.yml"]
