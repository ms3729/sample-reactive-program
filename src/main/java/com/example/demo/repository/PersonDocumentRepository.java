package com.example.demo.repository;

import com.example.demo.domain.entity.Person;
import com.example.demo.domain.entity.PersonDocument;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonDocumentRepository extends CrudRepository<PersonDocument, Long> {

    List<PersonDocument> findPersonDocumentsByPerson_Id(long id);

}
