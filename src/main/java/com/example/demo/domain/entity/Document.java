package com.example.demo.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
public class Document implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String docName;
    private String addDate;

    public Document() {
    }

    public Document(String docName, String addDate) {
        this.docName = docName;
        this.addDate = addDate;
    }
}
