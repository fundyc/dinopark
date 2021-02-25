package com.dinopark.aggregator;

import brave.baggage.BaggageField;
import com.dinopark.aggregator.model.Aggregator;
import com.dinopark.aggregator.model.Dinosaurs;
import com.dinopark.aggregator.model.Triceratop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AggregatorService {
    private static Logger log = LoggerFactory.getLogger(AggregatorService.class);
    private static String DINOSAURS_URI = "http://dinosaurs/dinosaurs";
    private static String TRICERATOPS_URI = "http://triceratops/triceratop";

    @Autowired
    private WebClient webClient;

    @Autowired
    private BaggageField versionField;


    Mono<Aggregator> getAggregator() {
        versionField.updateValue(getClass().getPackage().getImplementationVersion());
        return getDinosaurs()
                .onErrorReturn(null)
                .zipWith(getTriceratop().onErrorReturn(null),
                (dinosaurs, triceratop) -> new Aggregator(dinosaurs, triceratop));
    }

    private Mono<Dinosaurs> getDinosaurs() {
        log.info("Calling dinosaurs");
        return webClient.get()
                .uri(DINOSAURS_URI)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(Dinosaurs.class);
                    }
                    else {
                        return response.createException().flatMap(Mono::error);
                    }
                });
    }

    private Mono<Triceratop> getTriceratop() {
        log.info("Calling triceratops");
        return webClient.get()
                .uri(TRICERATOPS_URI)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(Triceratop.class);
                    }
                    else {
                        return response.createException().flatMap(Mono::error);
                    }
                });
    }
}
