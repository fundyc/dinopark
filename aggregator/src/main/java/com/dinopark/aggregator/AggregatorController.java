package com.dinopark.aggregator;

import com.dinopark.aggregator.model.Aggregator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AggregatorController {
    private static Logger log = LoggerFactory.getLogger(AggregatorController.class);
    @Autowired
    AggregatorService aggregatorService;

    @Autowired
    Tracer tracer;

    @GetMapping("/aggregator")
    public Mono<Aggregator> aggregator(ServerHttpResponse response) {
        log.info("Retrieve aggreagtor info");
        response.getHeaders().add("X-B3-TraceId", tracer.currentSpan().context().traceId());
        return aggregatorService.getAggregator();
    }

    @GetMapping()
    public Mono<String> hello(ServerHttpResponse response) {
        log.info("HELLO");
        response.getHeaders().add("X-B3-TraceId", tracer.currentSpan().context().traceId());
        return Mono.just("HELLO");
    }
}
