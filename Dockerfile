FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu

WORKDIR /app

COPY java.db ./
COPY Java_test_app.jar ./

ENV DISPLAY=host.docker.internal:0.0

CMD java -jar Java_test_app.jar