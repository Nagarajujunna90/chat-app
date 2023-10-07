FROM openjdk:17
EXPOSE 9091
ADD target/chat-app-0.0.1-SNAPSHOT.jar chat-app.jar
ENTRYPOINT ["java","-jar","/chat-app.jar"]