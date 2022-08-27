package com.example.demo.repository;

import com.example.demo.domain.entity.Document;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DocumentRepository extends CrudRepository<Document, Long> {

    List<Document> findDocumentById(long id);

}
