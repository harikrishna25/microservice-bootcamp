FROM eclipse-temurin:11-jre-alpine 
ADD target/currency-conversion.jar currency-conversion.jar
EXPOSE 9001
ENTRYPOINT [ "java", "-jar" , "currency-conversion.jar"]
