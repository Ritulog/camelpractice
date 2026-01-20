package com.ritu.camel.camelandkafka;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    ProducerTemplate producerTemplate;

    @PostMapping("/send")
    public String send(@RequestBody String msg) {
        producerTemplate.sendBody("direct:sendToKafka", msg);
        return "Message sent to Kafka";
    }
}
