package com.ritu.camel.eip;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class AggregatorRoute extends RouteBuilder {
    @Override
    public void configure() {

        from("file:/Users/ritu/Desktop/camelproject/aggInput?noop=false")
                .routeId("Aggregator Route")
                .unmarshal().json()
                .aggregate(jsonpath("$.customerId"), new OrderAggregationStrategy())
                .completionSize(3)   // jab 3 messages aa jaye
                .completionTimeout(5000) // ya 5 sec baad complete
                .marshal().json()
                .log("Aggregated Order: ${body}")
                .to("file:/Users/ritu/Desktop/camelproject/aggOutput");
    }
}
