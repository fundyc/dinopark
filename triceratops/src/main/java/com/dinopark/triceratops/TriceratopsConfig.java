package com.dinopark.triceratops;

import brave.baggage.BaggageField;
import brave.baggage.CorrelationScopeConfig;
import brave.context.slf4j.MDCScopeDecorator;
import brave.propagation.CurrentTraceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TriceratopsConfig {
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
