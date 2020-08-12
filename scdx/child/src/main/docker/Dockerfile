FROM docker.io/java:8
ADD *.jar app.jar
EXPOSE 8080
EXPOSE 8008
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]