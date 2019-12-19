package com.tiagoanacleto.booksapi.repository;

import com.tiagoanacleto.booksapi.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, Long> {

    Optional<Book> findBookByIsbn(Long isbn);

    @Query("{ 'title' : ?0, 'author': ?1, 'genre' : ?2 }")
    List<Book> getAllOrByGenreOrAuthorOrTitle(String title, String author, String genre);

}
