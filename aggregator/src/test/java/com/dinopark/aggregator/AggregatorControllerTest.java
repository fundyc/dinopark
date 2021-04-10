package com.dinopark.aggregator;

import com.dinopark.aggregator.model.Aggregator;
import com.dinopark.aggregator.model.Dinosaurs;
import com.dinopark.aggregator.model.Triceratop;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AggregatorControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void aggregator() {
        Aggregator aggregatorToTest = new Aggregator(new Dinosaurs(), new Triceratop());
        webTestClient.get()
                .uri("/aggregator")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Aggregator.class)
                .isEqualTo(aggregatorToTest);
    }

    @Test
    void hello() {
        webTestClient.get()
                .uri("/")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("HELLO");
    }
}