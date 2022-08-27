package com.example.demo.repository;

import com.example.demo.domain.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findPersonById(long id);


}
