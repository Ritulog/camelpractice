package com.ritu.camel.csvtomongo;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "crew")
public class Crew {
    @Id
    private String id;
    private String name;
    private String role;
    private String email;
}
