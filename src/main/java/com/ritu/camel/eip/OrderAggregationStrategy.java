package com.ritu.camel.eip;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

import java.util.ArrayList;
import java.util.List;

public class OrderAggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        if (oldExchange == null) {
            return newExchange;
        }

        List<Object> list;

        Object oldBody = oldExchange.getIn().getBody();
        Object newBody = newExchange.getIn().getBody();

        if (oldBody instanceof List) {
            list = (List<Object>) oldBody;
        } else {
            list = new ArrayList<>();
            list.add(oldBody);
        }

        list.add(newBody);
        oldExchange.getIn().setBody(list);
        return oldExchange;
    }
}
