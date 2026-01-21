package com.ritu.camel.mongokafkacamel;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean("myMongoBean")
    public MongoClient mongoClient() {

        return MongoClients.create("mongodb://localhost:27017");
    }
}
