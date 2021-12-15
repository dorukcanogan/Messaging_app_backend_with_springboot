FROM openjdk:8
COPY ./target/*.jar /usr/src/myapp/springjpa.jar
WORKDIR /usr/src/myapp
CMD ["java","-jar", "springjpa.jar"]