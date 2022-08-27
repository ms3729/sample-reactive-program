package com.example.demo.domain.service;

import com.example.demo.domain.entity.Document;
import com.example.demo.repository.DocumentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Mono<Document> addDocument(Document document){
        return Mono .defer(() -> Mono.just(documentRepository.save(document)));
    }
    public Flux<Document> getDocumentsByPersonId(long id){
        return Flux.defer(() -> Flux.fromIterable(documentRepository.findDocumentById(id)));
    }
    public Flux<Document> findAll(){
        return Flux.defer(() -> Flux.fromIterable(documentRepository.findAll()));
    }
}
