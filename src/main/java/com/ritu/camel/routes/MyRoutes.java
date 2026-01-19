//package com.ritu.camel.routes;
//
//import org.apache.camel.builder.RouteBuilder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MyRoutes extends RouteBuilder {
//    @Override
//    public void configure() throws Exception {
//
//        onException(Exception.class)
//                .log("Exception occurred: ${exception.message}")
//                .process(exchange -> {
//                    exchange.getContext().stop(); // stops Camel context
//                })
//                .handled(true);   // VERY IMPORTANT
//
//        from("file:/Users/ritu/Desktop/camelproject/input?noop=true")
//                .routeId("file-transfer-route")
//                .process(exchange -> {
//                    String body = exchange.getIn().getBody(String.class);
//                    Long fileSize = exchange.getIn().getHeader("CamelFileLength", Long.class);
//
//                    if (fileSize == null || fileSize == 0 || body == null || body.trim().isEmpty()) {
//                        throw new RuntimeException("File content is empty");
//                    }
//
//                    exchange.getIn().setBody(body.toUpperCase());
//                })
//                .log("Processing file: ${header.CamelFileName}")
//                .log("file content : ${body}")
//                .to("file:/Users/ritu/Desktop/camelproject/output");
//    }
//
//    // @Override
////    public void configure() throws Exception {
////
////        from("file:/Users/ritu/Desktop/camelproject/input?noop=true")
////                .routeId("file-transfer-route")
////
////                .doTry()
////                .process(exchange -> {
////                    String body = exchange.getIn().getBody(String.class);
////
////                    // simulate exception
////                    if (body == null || body.trim().isEmpty()) {
////                        System.out.println("nnnnnnnn");
////                        throw new RuntimeException("File content is empty");
////
////                    }
////
////                    exchange.getIn().setBody(body.toUpperCase());
////                })
////                .log("Processing file: ${header.CamelFileName}")
////                .log("File content : ${body}")
////                .to("file:/Users/ritu/Desktop/camelproject/output")
////
////                .doCatch(Exception.class)
////                .log("Error occurred in file-transfer-route")
////                .log("Exception message: ${exception.message}")
////                .to("file:/Users/ritu/Desktop/camelproject/error")
////
////                .doFinally()
////                .log("Route execution completed for file: ${header.CamelFileName}")
////                .end();
////    }
//
//
//}
