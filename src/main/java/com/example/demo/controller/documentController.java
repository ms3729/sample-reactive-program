package com.example.demo.controller;

import com.example.demo.domain.entity.Document;
import com.example.demo.domain.service.DocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping(value = "/v1")
public class documentController {

    private final DocumentService DocumentService;

    public documentController(DocumentService DocumentService) {
        this.DocumentService = DocumentService;
    }

    @GetMapping("/documents")
    public Flux<Document> getAllDocuments() {
        return DocumentService.findAll();
    }

    @GetMapping("/documents/person/{id}")
    public Flux<Document> getAllDocumentsByPersonId(@PathVariable("id") long id) {
        return DocumentService.getDocumentsByPersonId(id);
    }

    @PostMapping("/document")
    public Mono<Document> addDocument(@RequestBody Document Document) {
        return DocumentService.addDocument(Document);
    }
}
