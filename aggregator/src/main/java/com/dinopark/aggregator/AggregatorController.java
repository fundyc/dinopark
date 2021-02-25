package com.dinopark.aggregator;

import com.dinopark.aggregator.model.Aggregator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AggregatorController {
    private static Logger log = LoggerFactory.getLogger(AggregatorController.class);
    @Autowired
    AggregatorService aggregatorService;

    @GetMapping("/aggregator")
    public Mono<Aggregator> aggregator() {
        log.info("Retrieve aggreagtor info");
        return aggregatorService.getAggregator();
    }
}
