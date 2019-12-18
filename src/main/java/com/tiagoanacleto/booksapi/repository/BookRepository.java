package com.tiagoanacleto.booksapi.repository;

import com.tiagoanacleto.booksapi.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, Long> {
}
