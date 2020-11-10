FROM java:8

MAINTAINER fuzz<2279419934@qq.com>

COPY "*.jar" "/app.jar"

WORKDIR "/"

ENTRYPOINT ["java", "-jar", "/app.jar"]