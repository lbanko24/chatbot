# chatbot

Install Ab:
```console
mvn install:install-file -Dfile=./lib/Ab.jar -DgroupId=com.chatbots -DartifactId=Ab -Dversion=1.0 -Dpackaging=jar
```

Generate jar file:
```console
mvn package
```

Execute with load balancer and database:
```console
docker-compose up
```
