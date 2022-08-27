package com.example.demo;

import com.example.demo.domain.entity.Document;
import com.example.demo.domain.entity.Person;
import com.example.demo.domain.entity.PersonDocument;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.repository.PersonDocumentRepository;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataInitializer {

    private final PersonRepository personRepository;

    private final DocumentRepository documentRepository;

    private final PersonDocumentRepository personDocumentRepository;

    public DataInitializer(PersonRepository personRepository,
                           DocumentRepository documentRepository,
                           PersonDocumentRepository personDocumentRepository) {
        this.personRepository = personRepository;
        this.documentRepository = documentRepository;
        this.personDocumentRepository = personDocumentRepository;
    }


    void initialize() {
        List<Person> personList = new ArrayList<>();
        Person ehsan = new Person("Ehsan");
        personList.add(ehsan);
        Person mohammad = new Person("Mohammad");
        personList.add(mohammad);
        Person hosein = new Person("Hosein");
        personList.add(hosein);
        Person ali = new Person("Ali");
        personList.add(ali);
        personRepository.saveAll(personList);

        List<Document> documents = new ArrayList<>();
        Document doc1 = new Document("karnameh", "1401/5/12");
        documents.add(doc1);
        Document doc2 = new Document("shenasname", "1401/4/12");
        documents.add(doc2);
        Document doc3 = new Document("bime", "1401/3/12");
        documents.add(doc3);
        Document doc4 = new Document("madrak tahsili", "1401/2/12");
        documents.add(doc4);
        documentRepository.saveAll(documents);

        Random rand = new Random();
        /*List<PersonDocument> personDocuments = new ArrayList<>();
        PersonDocument pd1 = new PersonDocument(ali, documents.get(rand.nextInt(documents.size())), "1401/5/1");
        personDocuments.add(pd1);
        PersonDocument pd2 = new PersonDocument(mohammad, documents.get(rand.nextInt(documents.size())), "1401/5/2");
        personDocuments.add(pd2);
        PersonDocument pd3 = new PersonDocument(hosein, documents.get(rand.nextInt(documents.size())), "1401/5/3");
        personDocuments.add(pd3);

        personDocumentRepository.saveAll(personDocuments);*/
        Set<Document> personDocumentSet;
        personDocumentSet =new HashSet<>();
        personDocumentSet.add(documents.get(rand.nextInt(documents.size())));
        mohammad.setDocuments(personDocumentSet);
        personRepository.save(mohammad);
        personDocumentSet =new HashSet<>();
        personDocumentSet.add(documents.get(rand.nextInt(documents.size())));
        ali.setDocuments(personDocumentSet);
        personRepository.save(ali);
        personDocumentSet =new HashSet<>();
        personDocumentSet.add(documents.get(rand.nextInt(documents.size())));
        hosein.setDocuments(personDocumentSet);
        personRepository.save(hosein);
    }

}
