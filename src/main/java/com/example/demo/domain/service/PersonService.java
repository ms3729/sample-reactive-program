package com.example.demo.domain.service;


import com.example.demo.domain.dto.PersonDTO;
import com.example.demo.domain.entity.Document;
import com.example.demo.domain.entity.Person;
import com.example.demo.repository.PersonDocumentRepository;
import com.example.demo.repository.PersonRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonDocumentRepository personDocumentRepository;

    public PersonService(PersonRepository personRepository,
                         PersonDocumentRepository personDocumentRepository) {
        this.personRepository = personRepository;
        this.personDocumentRepository = personDocumentRepository;
    }

    public Mono<PersonDTO> findById(long id) {
        Person person = personRepository.findById(id).orElse(null);
        List<String> docNames = person.getDocuments().stream().filter(Objects::nonNull).map(Document::getDocName).collect(Collectors.toList());
        PersonDTO personDTO = new PersonDTO(person.getId(), person.getName(), docNames);
        return Mono.defer(() -> Mono.justOrEmpty(personDTO));
    }

    public Flux<PersonDTO> findAll() {
        List<Person> personList = (List<Person>) personRepository.findAll();
        List<PersonDTO> personDTOS = personList.stream().map(
                person -> {
                    List<String> docNames = person.getDocuments().stream().filter(Objects::nonNull).map(Document::getDocName).collect(Collectors.toList());
                    return new PersonDTO(person.getId(), person.getName(), docNames);
                }
        ).collect(Collectors.toList());
        return Flux.defer(() -> Flux.fromIterable(personDTOS));
    }

    public Mono<Person> addPerson(Person person) {
        return Mono.defer(() -> Mono.just(personRepository.save(person)));
    }

    public Mono<PersonDTO> editPerson(PersonDTO personDTO) {
        Optional<Person> editPerson = personRepository.findById(personDTO.getId());
        if (editPerson.isPresent()) {
            editPerson.get().setName(personDTO.getName());
           Person person = personRepository.save(editPerson.get());
            if (Objects.nonNull(person)) {
                List<String> docNames = person.getDocuments().stream().filter(Objects::nonNull).map(Document::getDocName).collect(Collectors.toList());
                return Mono.defer(() -> Mono.just(new PersonDTO(person.getId(), person.getName(), docNames)));
            }
        }
        return null;
    }

    public Flux<Person> getPersonsByDocumentId(long id) {
        return Flux.defer(() -> Flux.fromIterable(personRepository.findPersonById(id)));
    }


}
