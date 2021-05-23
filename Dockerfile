FROM openjdk:8
COPY ./target/user-service-*.jar user-service.jar
EXPOSE 9112
CMD ["java","-jar","user-service.jar"]

