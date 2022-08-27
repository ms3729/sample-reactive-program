package com.example.demo.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class PersonDocument implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Person person;

    @ManyToOne
    private Document document;
    private String assignDate;

    public PersonDocument() {
    }

    public PersonDocument(Person person, Document document, String assignDate) {
        this.person = person;
        this.document = document;
        this.assignDate = assignDate;
    }
}
