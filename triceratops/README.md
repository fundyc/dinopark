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
docker build -t triceratops .
```

Run image
```
docker run --name triceratops --rm --init -p 8082:8082 triceratops
ctrl-c to close
```