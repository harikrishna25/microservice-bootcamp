FROM eclipse-temurin:11-jre-alpine 
ADD target/manage-currency.jar manage-currency.jar
EXPOSE 9000
ENTRYPOINT [ "java", "-jar" , "manage-currency.jar"]
