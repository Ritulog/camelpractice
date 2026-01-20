package com.ritu.camel.camelandkafka;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("kafka:order-topic?brokers=localhost:9092&groupId=camel-group")
            .routeId("kafka-consumer")
            .log("Consumed from Kafka: ${body}");
    }
}
