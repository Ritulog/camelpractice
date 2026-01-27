package com.ritu.camel.csvtomongo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CsvToMongoRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // Error Handling
        onException(Exception.class)
            .log("Error processing CSV: ${exception.message}")
            .handled(true);

        // CSV to MongoDB
        from("file:/Users/ritu/Desktop/camelproject/input?fileName=crew.csv&noop=true")
            .unmarshal().csv() // parse CSV
            .split(body())     // split into rows
            .process(exchange -> {
                var row = exchange.getIn().getBody(java.util.List.class);
                Crew crew = new Crew();
                crew.setId((String) row.get(0));
                crew.setName((String) row.get(1));
                crew.setRole((String) row.get(2));
                crew.setEmail((String) row.get(3));
                exchange.getIn().setBody(crew);
            })
            .to("mongodb:myMongoBean?database=flightdb&collection=crew&operation=insert")
            .log("Inserted crew: ${body}");
    }
}
