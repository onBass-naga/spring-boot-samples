FROM naga/java8

ADD ./build/libs/*.jar ./app.jar
EXPOSE 8080
CMD ["java", "-jar", "-Dspring.profiles.active=prod",  "./app.jar"]

