#FROM registry.redhat.io/ubi9/openjdk-17-runtime:latest
FROM amazoncorretto:17-alpine-jdk
WORKDIR /app
# Copia los archivos al contenedor
COPY target/*.jar ./app.jar
EXPOSE 8080
EXPOSE 9779
#ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar .
#ENV JAVA_TOOL_OPTIONS "-javaagent:./opentelemetry-javaagent.jar"
CMD echo "java $JAVA_OPTS -jar /app/app.jar" && \
        java $JAVA_OPTS  -jar /app/app.jar