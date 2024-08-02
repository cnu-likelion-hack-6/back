FROM amazoncorretto:21

WORKDIR /app

COPY ./build/libs/babap-1.0.jar /app/babap.jar

ENV TZ=Asia/Seoul

CMD ["java", "-jar", "babap.jar"]
