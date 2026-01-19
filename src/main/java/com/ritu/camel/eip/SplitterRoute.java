package com.ritu.camel.eip;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SplitterRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:/Users/ritu/Desktop/camelproject/jsonFolder?fileName=order.json&noop=false&idempotent=true")
                .routeId("spliter route")
                .log("FILE PICKED: ${header.CamelFileName}")
                .split().jsonpath("$.orders[*]")
                .marshal().json()
                .log("Processing order: ${body}")
                .setHeader("CamelFileName", simple("order-${exchangeProperty.CamelSplitIndex}.json"))
                .to("file:/Users/ritu/Desktop/camelproject/output")
                .end();

    }

}
