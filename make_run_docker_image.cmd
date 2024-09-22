mvn clean package
docker build -t spring-boot-docker-nflx .
docker run -it -p 8080:8080 spring-boot-docker-nflx