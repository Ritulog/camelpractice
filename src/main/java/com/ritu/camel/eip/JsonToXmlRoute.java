//package com.ritu.camel.eip;
//
//import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.model.dataformat.JsonLibrary;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JsonToXmlRoute extends RouteBuilder {
//
//    @Override
//    public void configure() {
//
//        from("file:/Users/ritu/Desktop/camelproject/jsonFolder?fileName=source.json&noop=true")
//            .routeId("json-to-xml-message-translator")
//
//            .log("JSON Input: ${body}")
//
//            // Message Translator (JSON → Java Map)
//            .unmarshal().json(JsonLibrary.Jackson)
//
//            // Message Translator (Java → XML)
//            .marshal().jacksonXml()
//
//            .log("XML Output: ${body}")
//
//            .to("file:/Users/ritu/Desktop/camelproject/output?fileName=output.xml");
//    }
//}
