package com.ritu.camel.camelandkafka;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
/*
 “User REST API se request bhejta hai
 Camel route us message ko Kafka topic me push karta hai
  Kafka asynchronously store karta hai
 Camel consumer usko process karta hai
 Isse system loosely coupled aur scalable banta hai” */




@Component
public class KafkaProducerRoute extends RouteBuilder {
    @Override
    public void configure() {

        from("direct:sendToKafka")
                .routeId("kafka-producer")
                .log("Sending to Kafka: ${body}")
                .to("kafka:order-topic?brokers=localhost:9092");
    }
}
