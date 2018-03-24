FROM  openjdk:8-jre-alpine
LABEL MAINTAINER="Initiative void*"
ENV JAVA_OPTS=-Dspring.profiles.active=production 
COPY ecg.backend-1.0-SNAPSHOT.jar /srv/opencodes/backend/ecg.backend-1.0-SNAPSHOT.jar
CMD java $JAVA_OPTS -jar /srv/opencodes/backend/ecg.backend-1.0-SNAPSHOT.jar
