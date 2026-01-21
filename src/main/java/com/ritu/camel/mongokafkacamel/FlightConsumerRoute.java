package com.ritu.camel.mongokafkacamel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FlightConsumerRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("kafka:flight-topic?brokers=localhost:9092&groupId=camel-flight-group")
            .routeId("flight-consumer-route")
            .log("Received flight event: ${body}")

            // JSON String → Map
            .unmarshal().json()

            // Add metadata
            .setHeader("processedBy", constant("camel-flight-service"))
            .setHeader("processedTime", simple("${date:now:yyyy-MM-dd'T'HH:mm:ss}"))

            // Save to MongoDB
            .to("mongodb:myMongoBean?database=flightdb&collection=flights&operation=insert")

            .log("Flight saved to MongoDB");
    }
}
