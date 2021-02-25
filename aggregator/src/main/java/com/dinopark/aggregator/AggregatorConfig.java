package com.dinopark.aggregator;

import brave.baggage.BaggageField;
import brave.baggage.CorrelationScopeConfig;
import brave.context.slf4j.MDCScopeDecorator;
import brave.propagation.CurrentTraceContext;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AggregatorConfig {
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }

    @Bean
    public BaggageField versionField() {
        return BaggageField.create("version");
    }

    @Bean
    CurrentTraceContext.ScopeDecorator mdcScopeDecorator() {
        return MDCScopeDecorator.newBuilder()
                .clear()
                .add(CorrelationScopeConfig.SingleCorrelationField.newBuilder(versionField())
                        .flushOnUpdate()
                        .build())
                .build();
    }

}
