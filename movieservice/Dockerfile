FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=moviedb
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/moviedb
ENV MYSQL_DRIVER=com.mysql.jdbc.Driver
ENV MYSQL_DIALECT=org.hibernate.dialect.MySQL5InnoDBDialect
ADD ./target/moviecruiserserver-0.0.1-SNAPSHOT.jar /usr/src/moviecruiserserver-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "moviecruiserserver-0.0.1-SNAPSHOT.jar"]
