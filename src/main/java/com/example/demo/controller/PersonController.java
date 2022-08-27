package com.example.demo.controller;

import com.example.demo.domain.dto.PersonDTO;
import com.example.demo.domain.entity.Person;
import com.example.demo.domain.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping(value = "/v1")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/person/{id}")
    public Mono<PersonDTO> getPerson(@PathVariable("id") Long id) {
        return personService.findById(id);
    }

    @GetMapping("/persons")
    public Flux<PersonDTO> getAllPersons() {
        return personService.findAll();
    }

    @GetMapping("/persons/document/{id}")
    public Flux<Person> getAllPersonsByDocumentId(@PathVariable("id") long id) {
        return personService.getPersonsByDocumentId(id);
    }

    @PostMapping("/person")
    public Mono<ResponseEntity<Person>> addPerson(@RequestBody Person person) {
        return personService.addPerson(person).map(p -> new ResponseEntity<>(p, HttpStatus.CREATED));
    }

    @PutMapping("/person")
    public Mono<ResponseEntity<PersonDTO>> editPerson(@RequestBody PersonDTO personDTO) {
        return personService.editPerson(personDTO).map(p -> new ResponseEntity<>(p, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
