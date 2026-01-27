package com.ritu.camel.csvtomongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CrewRepository extends MongoRepository<Crew, String> {

}
