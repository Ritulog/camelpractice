package com.ritu.camel.exception;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class GlobalExceptionHandler extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .handled(true)   // exception ko consume kar lega
                .log("GLOBAL EXCEPTION OCCURRED")
                .log("Route Id: ${routeId}")
                .log("Exception Message: ${exception.message}")
                .to("file:/Users/ritu/Desktop/camelproject/error");

        // Other routes bhi yahin ya alag class me ho sakte hain
    }
}
