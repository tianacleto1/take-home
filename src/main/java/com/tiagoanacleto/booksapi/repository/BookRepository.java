package com.tiagoanacleto.booksapi.repository;

import com.tiagoanacleto.booksapi.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, Long> {

    Optional<Book> findBookByIsbn(Long isbn);
}
