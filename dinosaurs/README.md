Run zipkin server:
```
docker run -d -p 9411:9411 openzipkin/zipkin
```

Run eureka service discovery:
```
docker run -d -p 8761:8761 springcloud/eureka
```

Build image
```
docker build -t dinosaurs .
```

Run image
```
docker run --name dinosaurs --rm --init -p 8081:8081 dinosaurs
ctrl-c to close
```