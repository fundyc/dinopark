package com.dinopark.aggregator.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class AddHeaderFilter implements WebFilter {

    private static Logger log = LoggerFactory.getLogger(AddHeaderFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        log.info("AT THIS POINT TRACER IS HAS NOT GENERATED TRACE_ID");
        serverWebExchange.getResponse().getHeaders().add("X-Service-Name", "Aggregator");
        return webFilterChain.filter(serverWebExchange);
    }
}
