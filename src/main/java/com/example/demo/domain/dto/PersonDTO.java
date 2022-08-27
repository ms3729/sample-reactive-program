package com.example.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
public class PersonDTO {
    private long id;
    private String name;
    private List<String> docNames;

    public PersonDTO() {
    }

    public PersonDTO(long id, String name, List<String> docNames) {
        this.id = id;
        this.name = name;
        this.docNames = docNames;
    }
}
