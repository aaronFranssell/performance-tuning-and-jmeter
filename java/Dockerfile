FROM eclipse-temurin:21

ARG APP_DIR=/home/java
RUN mkdir -p ${APP_DIR}
WORKDIR ${APP_DIR}
ADD --chown=app:app . .

RUN ./gradlew build