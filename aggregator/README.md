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
docker build -t aggregator .
```

Run image
```
docker run --name aggregator --rm --init -p 8080:8080 aggregator
ctrl-c to close
```

Enter in an image
```
docker exec -it aggregator /bin/bash
```

Run docker compose with dependencies
```
docker-compose run --rm start_dependencies

```

Run docker compose and rebuild
```
docker-compose up --build triceratops dinosaurs aggregator
```
